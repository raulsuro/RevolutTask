package com.raul.revolutcodetask.presentation.adapter.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation

@BindingAdapter("countryImage")
fun loadProductImage(view: ImageView, imageUrl: String) {
    view.load(imageUrl) {
        transformations(CircleCropTransformation())
    }

}
