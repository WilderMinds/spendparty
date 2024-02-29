package com.samdev.spendparty.extensions

import com.samdev.spendparty.domain.models.Timeline
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.Calendar
import java.util.Date

class DateExtensionsKtTest {

    @Test
    fun `test future date in days returns appropriate timeline`() {
        val futureDate = Calendar.getInstance().let {
            it.add(Calendar.DAY_OF_YEAR, 3)
            it.time
        }.toRelativeDate()
        val expectedTimeline = Timeline.RelativeDate.Days(3)
        assertEquals(expectedTimeline, futureDate)
        assertEquals(false, futureDate.isOverdue)
    }

    @Test
    fun `test future date in weeks returns appropriate timeline`() {
        val futureDate = Calendar.getInstance().let {
            it.add(Calendar.WEEK_OF_YEAR, 3)
            it.time
        }.toRelativeDate()
        val expectedTimeline = Timeline.RelativeDate.Weeks(3)
        assertEquals(expectedTimeline, futureDate)
        assertEquals(false, futureDate.isOverdue)
    }

    @Test
    fun `test future date in less than 3 months returns appropriate timeline`() {
        val futureDate = Calendar.getInstance().let {
            it.add(Calendar.WEEK_OF_YEAR, 11)
            it.time
        }.toRelativeDate()
        val expectedTimeline = Timeline.RelativeDate.Weeks(11)
        assertEquals(expectedTimeline, futureDate)
        assertEquals(false, futureDate.isOverdue)
    }

    @Test
    fun `test future date in more than 3 months returns appropriate timeline`() {
        val futureDate = Calendar.getInstance().let {
            it.add(Calendar.WEEK_OF_YEAR, 40)
            it.time
        }.toRelativeDate()
        val expectedTimeline = Timeline.RelativeDate.Months(10)
        assertEquals(expectedTimeline, futureDate)
        assertEquals(false, futureDate.isOverdue)
    }

    @Test
    fun `test past date returns appropriate timeline with overdue as true`() {
        val pastDate = Calendar.getInstance().let {
            it.add(Calendar.DAY_OF_YEAR, -3)
            it.time
        }.toRelativeDate()
        val expectedOverdueTimeline = Timeline.RelativeDate.Days(-3)
        assertEquals(expectedOverdueTimeline, pastDate)
        assertEquals(true, pastDate.isOverdue)
    }

    @Test
    fun `test current date returns appropriate timeline`() {
        val currentDate = Date().toRelativeDate()
        val expected = Timeline.RelativeDate.Days(0)
        assertEquals(expected, currentDate)
        assertEquals(false, currentDate.isOverdue)
    }
}