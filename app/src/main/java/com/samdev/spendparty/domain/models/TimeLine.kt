package com.samdev.spendparty.domain.models

sealed interface Timeline {
    object Infinity : Timeline

    sealed interface RelativeDate : Timeline {
        val number: Int
        val isOverdue: Boolean
            get() = number < 0

        data class Days(override val number: Int) : RelativeDate
        data class Weeks(override val number: Int) : RelativeDate
        data class Months(override val number: Int) : RelativeDate
    }
}