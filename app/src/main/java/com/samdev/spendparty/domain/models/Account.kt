package com.samdev.spendparty.domain.models

import com.samdev.spendparty.extensions.formatToCurrency
import com.samdev.spendparty.extensions.toTimeline
import java.util.Date

data class Account(
    val id: Int,
    val name: String,
    val currency: String,
    val balance: Long,
    val targetAmount: Long,
    val targetDate: Date?
) {
    val displayBalance: String
        get() = "$currency ${balance.formatToCurrency()}"

    val displayTargetAmount: String
        get() = "$currency ${targetAmount.formatToCurrency()}"

    val percentageComplete: Float
        get() = balance.toFloat() / targetAmount.toFloat()

    val percentageCompleteDisplay: String
        get() = "${(percentageComplete.toDouble() * 100).toInt()}%"

    val timeLeft: Timeline
        get() = targetDate.toTimeline()
}


