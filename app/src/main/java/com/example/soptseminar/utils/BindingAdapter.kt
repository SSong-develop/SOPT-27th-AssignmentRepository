package com.example.soptseminar.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:image")
    fun imageBind(view : ImageView , imageUrl : String){
        Glide.with(view)
            .load(imageUrl)
            .into(view)
    }
}