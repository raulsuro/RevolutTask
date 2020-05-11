package com.raul.revolutcodetask.presentation.util

fun CharSequence.formatDouble():Double {
    return this.toString().replace(',', '.').toDouble()
}
