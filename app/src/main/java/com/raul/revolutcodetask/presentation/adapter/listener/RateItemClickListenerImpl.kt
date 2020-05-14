package com.raul.revolutcodetask.presentation.adapter.listener

import android.content.Context
import android.view.View
import com.raul.revolutcodetask.presentation.activities.MainActivity
import com.raul.revolutcodetask.presentation.model.RateView
import javax.inject.Inject

class RateItemClickListenerImpl @Inject constructor() : RateItemClickListener {
    override fun onRateClick(context: Context, view: View, item: RateView, position: Int) {
        (context as MainActivity).onRateClick(context, view, item, position)
    }
}