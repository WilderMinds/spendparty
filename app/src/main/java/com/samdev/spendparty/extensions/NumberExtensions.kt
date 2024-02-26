package com.samdev.spendparty.extensions


fun Number.formatToCurrency(): String {
    return String.format("%,.2f", this.toDouble())
}