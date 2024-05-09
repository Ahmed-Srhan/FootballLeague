package com.srhan.footballleague.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.srhan.footballleague.R


@BindingAdapter(value = ["app:imageUrl"])
fun setImageUrl(imageView: ImageView, url: String) {
    imageView.load(url) {
        placeholder(R.drawable.ic_launcher_foreground)
    }
}