package com.conrradocamacho.orgs.extensions

import android.widget.TextView
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun TextView.formatPrice(price: BigDecimal) {
    val formatter: NumberFormat = NumberFormat
        .getCurrencyInstance(Locale("pt", "br"))
    this.text = formatter.format(price)
}