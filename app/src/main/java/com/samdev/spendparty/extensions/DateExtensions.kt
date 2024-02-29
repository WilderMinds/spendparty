package com.samdev.spendparty.extensions

import com.samdev.spendparty.domain.models.Timeline
import java.util.Date
import kotlin.math.abs
import kotlin.math.roundToInt


fun Date?.toTimeline(): Timeline {
    return this?.toRelativeDate() ?: Timeline.Infinity
}

fun Date.toRelativeDate(): Timeline.RelativeDate {
    val currentTime = System.currentTimeMillis()
    val timestamp = time
    val differenceMillis = timestamp - currentTime

    val seconds = differenceMillis.toDouble() / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7
    val months = weeks / 4

    return when {
        // show only if greater than 3 months, else default to weeks
        months.absolute() >= 3 -> Timeline.RelativeDate.Months(months.roundToInt())
        weeks.absolute() >= 1 -> Timeline.RelativeDate.Weeks(weeks.roundToInt())
        else -> Timeline.RelativeDate.Days(days.roundToInt())
    }
}

private fun Double.absolute() = abs(this)