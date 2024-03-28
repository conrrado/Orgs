package com.conrradocamacho.orgs.extensions

import android.widget.ImageView
import coil.load
import com.conrradocamacho.orgs.R

fun ImageView.tryLoadingImage(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro) // image null
        error(R.drawable.erro) // image not exist
        placeholder(R.drawable.placeholder) // before loading image
    }
}