package com.vaibhav.githubclone

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert.assertEquals

@RunWith(JUnit4::class)
class FormatterClassTest {

    private fun getPastTime(secondToMinus: Long): String {
        val sampleDate =
            LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant().minusSeconds(secondToMinus)
        val formatter = DateTimeFormatter.ofPattern(FormatterClass.DATE_TIME_PATTERN).withLocale(
            Locale.getDefault()
        ).withZone(ZoneId.from(ZoneOffset.UTC))
        return formatter.format(sampleDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in seconds`() {
        val sampleDateString = getPastTime(5)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated few seconds ago", formattedDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in minutes`() {
        val sampleDateString = getPastTime(93)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated 1 minutes ago", formattedDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in hours`() {
        val sampleDateString = getPastTime(12304)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated 3 hours ago", formattedDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in days`() {
        val sampleDateString = getPastTime(634982)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated 7 days ago", formattedDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in months`() {
        val sampleDateString = getPastTime(2634568)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated 1 months ago", formattedDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in years`() {
        val sampleDateString = getPastTime(64023000)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated 2 years ago", formattedDate)
    }
}