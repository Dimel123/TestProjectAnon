package com.dmitry.grishin.testprojectanon.utils

import android.content.Context
import android.widget.ImageView
import com.dmitry.grishin.testprojectanon.R
import com.squareup.picasso.Picasso

fun setupImage(context: Context, url: String?, imageView: ImageView){
    if(url.isNullOrEmpty()) return
    Picasso.with(context)
            .load(url)
            .placeholder(R.drawable.ic_grey_square)
            .error(R.drawable.ic_grey_square)
            .into(imageView)
}