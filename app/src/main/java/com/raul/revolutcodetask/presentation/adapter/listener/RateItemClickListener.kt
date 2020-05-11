package com.raul.revolutcodetask.presentation.adapter.listener

import android.content.Context
import android.view.View
import com.raul.revolutcodetask.presentation.model.RateView

interface RateItemClickListener {
    fun onRateClick(context: Context, view: View, item: RateView, position: Int)
}