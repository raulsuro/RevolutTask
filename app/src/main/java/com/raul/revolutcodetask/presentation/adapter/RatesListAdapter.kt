package com.raul.revolutcodetask.presentation.adapter

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.raul.revolutcodetask.BR
import com.raul.revolutcodetask.R
import com.raul.revolutcodetask.presentation.adapter.listener.RateItemClickListener
import com.raul.revolutcodetask.presentation.model.CountryCurrencies
import com.raul.revolutcodetask.presentation.model.RateView
import com.raul.revolutcodetask.presentation.util.formatDouble
import kotlinx.android.synthetic.main.rates_list_item.view.*
import javax.inject.Inject

class RatesListAdapter @Inject constructor(
    private val rateItemClickListener: RateItemClickListener
) : RecyclerView.Adapter<RatesListAdapter.RatesViewHolder>() {

    private val ratesList = ArrayList<RateView>()

    fun setItems(someData: CountryCurrencies) {
        if (ratesList.isEmpty()) {
            ratesList.addAll(someData.rate)
            notifyDataSetChanged()
        } else {
            someData.rate.filter { it.currency != someData.baseCurrency }.forEach {
                replaceItems(it)
            }
            notifyItemRangeChanged(1, ratesList.size - 1)
        }
    }

    private fun searchItem(name: String): Int {
        for (i in 0 until ratesList.size) {
            if (name == ratesList[i].currency) {
                return i
            } else {
                println("Could not find name")
            }
        }
        return -1
    }

    private fun replaceItems(it: RateView) {
        it.calculateAmount(ratesList[0].amount)
        if (searchItem(it.currency) != -1)
            ratesList[searchItem(it.currency)] = it
        else
            ratesList.add(it)
    }

    fun swapItem(fromPosition: Int, toPosition: Int) {
        val itemToTop = ratesList.removeAt(fromPosition);
        ratesList.add(0, itemToTop)
        itemToTop.amount = itemToTop.calculatedAmount
        notifyItemMoved(fromPosition, toPosition)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        return RatesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rates_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return ratesList.size
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        val item = ratesList[position]
        holder.bind(item, position, this.rateItemClickListener)
    }

    inner class RatesViewHolder(
        private val viewDataBinding: ViewDataBinding
    ) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(item: RateView, position: Int, listener: RateItemClickListener) {
            viewDataBinding.root.amount_edittext.addTextChangedListener(customTextWatcher())

            viewDataBinding.root.setOnClickListener {
                listener.onRateClick(it.context, it, item, position)
            }

            viewDataBinding.setVariable(BR.rate, item)
            viewDataBinding.executePendingBindings()
        }
    }

    private fun RatesViewHolder.customTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (adapterPosition == 0) {
                    if (s.isNotEmpty()) {
                        ratesList[adapterPosition].amount =
                            s.formatDouble()

                        ratesList.map {
                            replaceItems(it)
                        }
                    } else {
                        ratesList.map {
                            ratesList[adapterPosition].amount = 0.0
                            it.calculatedAmount = 0.0
                        }
                    }
                    Handler().post { notifyItemRangeChanged(1, ratesList.size - 1) }
                }
            }
        }
    }
}