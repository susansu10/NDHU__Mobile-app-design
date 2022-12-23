package com.example.app_cityweather

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("setImage")

fun ImageView.setIconImage(icon: String?) {
    val accessURL = "${WeatherViewModel.ICON_URL}${icon}@2x.png"
    val iconUri = accessURL.toUri().buildUpon().scheme("https").build()

    if (icon == null){
        setImageDrawable(null)
    }
    else {
        this.load(iconUri)
    }
}