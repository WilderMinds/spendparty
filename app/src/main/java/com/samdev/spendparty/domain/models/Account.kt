package com.samdev.spendparty.domain.models

import com.samdev.spendparty.extensions.formatToCurrency
import java.time.LocalDate

data class Account(
    val id: Int,
    val name: String,
    val currency: String,
    val balance: Long,
    val targetAmount: Long,
    val targetDate: LocalDate
) {
    val displayBalance: String
        get() = "$currency ${balance.formatToCurrency()}"

    val displayTargetAmount: String
        get() = "$currency ${targetAmount.formatToCurrency()}"

    val balanceOfTarget: String
        get() = "$displayBalance / $displayTargetAmount"

    val percentageComplete: Float
        get() = balance.toFloat() / targetAmount.toFloat()

    val percentageCompleteDisplay: String
        get() = "${(percentageComplete.toDouble() * 100).toInt()}%"

    val timeLeft: Timeline
        get() = Timeline.TimeLeft.Weeks(72)
}

sealed interface Timeline {
    object Infinity: Timeline

    sealed interface TimeLeft: Timeline {
        val number: Int
        data class Days(override val number: Int): TimeLeft
        data class Weeks(override val number: Int): TimeLeft
    }
}


