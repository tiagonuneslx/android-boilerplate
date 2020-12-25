package com.example.androidboilerplate.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.androidboilerplate.R

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        setImageResource(R.drawable.ic_broken_image)
        return
    }
    val glideUrl = GlideUrl(
        imageUrl, LazyHeaders.Builder()
            .addHeader("User-Agent", "Android")
            .build()
    )
    Glide.with(context)
//            .load(imageUrl)
        .load(glideUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(this)
}