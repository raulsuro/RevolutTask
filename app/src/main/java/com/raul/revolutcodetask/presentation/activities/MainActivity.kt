package com.raul.revolutcodetask.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar
import com.raul.revolutcodetask.R
import com.raul.revolutcodetask.domain.model.ErrorEntity
import com.raul.revolutcodetask.domain.model.state.ScreenState
import com.raul.revolutcodetask.presentation.adapter.RatesListAdapter
import com.raul.revolutcodetask.presentation.interactor.MainViewModel
import com.raul.revolutcodetask.presentation.interactor.factory.ViewModelFactory
import com.raul.revolutcodetask.presentation.model.CountryCurrencies
import com.raul.revolutcodetask.presentation.model.RateView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var adapter: RatesListAdapter

    private var code = "CAD"
    private var snackbar: Snackbar? = null
    private var retries = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        viewModel.initialize(code)
        observeList()
    }

    private fun observeList() {
        viewModel.results.observe(this, Observer { resource ->
            when (resource) {
                is ScreenState.Loading -> showLoading()

                is ScreenState.Success -> {
                    hideLoading()
                    displayProducts(resource.data)
                }
                is ScreenState.Error -> {
                    hideLoading()
                    showError(resource.throwable)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        ratesList.layoutManager = LinearLayoutManager(this)
        ratesList.setHasFixedSize(true)
        ratesList.setItemViewCacheSize(32)
        (ratesList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        ratesList.adapter = adapter
    }

    private fun hideLoading() {
        stopShimmerEffect()
    }

    private fun displayProducts(data: CountryCurrencies) {
        code = data.baseCurrency
        retries = 1
        adapter.setItems(data)
    }

    private fun showLoading() {
        startShimmerEffect()
    }

    private fun showError(error: ErrorEntity) {
        val errorMessage = when (error) {
            is ErrorEntity.Network -> getString(R.string.network_error_message)
            is ErrorEntity.ServiceUnavailable -> getString(R.string.noservice_error_message)
            is ErrorEntity.Unknown -> getString(R.string.unknown_error_message)
            is ErrorEntity.Cancellation -> ""
            else -> getString(R.string.unknown_error_message)
        }
        if (errorMessage.isNotEmpty()) setSnackbar(errorMessage)
    }

    private fun setSnackbar(errorMessage: String) {
        var composeMessageError = errorMessage
        when (retries) {
            in 1..2 -> composeMessageError =
                String.format(getString(R.string.compose_error_message, errorMessage, retries))
        }
        if (snackbar == null) {
            snackbar = Snackbar.make(
                mainview, composeMessageError,
                Snackbar.LENGTH_INDEFINITE
            )
            snackbar!!.show()
        } else {
            if (retries == 3) {
                snackbar!!.setAction("Retry") {
                    retries = 0
                    showLoading()
                    viewModel.initialize(code)
                    snackbar!!.setAction("Action", null)
                }.setText(composeMessageError).show()
            } else {
                snackbar!!.setText(composeMessageError).show()
                retries++
            }
        }
    }

    private fun startShimmerEffect() {
        parentShimmerLayout.visibility = View.VISIBLE
        parentShimmerLayout.startShimmer()
    }

    private fun stopShimmerEffect() {
        parentShimmerLayout.stopShimmer()
        parentShimmerLayout.visibility = View.GONE
    }

    fun onRateClick(context: MainActivity, view: View, item: RateView, position: Int) {
        if (position != 0) {
            adapter.swapItem(position, 0)
            ratesList.layoutManager?.scrollToPosition(0);
            viewModel.initialize(item.currency)
        }
    }
}
