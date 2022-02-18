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

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in seconds`() {
        val sampleDate = LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant().minusSeconds(5)
        val formatter = DateTimeFormatter.ofPattern(FormatterClass.DATE_TIME_PATTERN).withLocale(
            Locale.getDefault()
        ).withZone(ZoneId.from(ZoneOffset.UTC))
        val sampleDateString = formatter.format(sampleDate)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated few seconds ago", formattedDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in minutes`() {
        val sampleDate = LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant().minusSeconds(93)
        val formatter = DateTimeFormatter.ofPattern(FormatterClass.DATE_TIME_PATTERN).withLocale(
            Locale.getDefault()
        ).withZone(ZoneId.from(ZoneOffset.UTC))
        val sampleDateString = formatter.format(sampleDate)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated 1 minutes ago", formattedDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in hours`() {
        val sampleDate =
            LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant().minusSeconds(12304)
        val formatter = DateTimeFormatter.ofPattern(FormatterClass.DATE_TIME_PATTERN).withLocale(
            Locale.getDefault()
        ).withZone(ZoneId.from(ZoneOffset.UTC))
        val sampleDateString = formatter.format(sampleDate)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated 3 hours ago", formattedDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in days`() {
        val sampleDate =
            LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant().minusSeconds(634982)
        val formatter = DateTimeFormatter.ofPattern(FormatterClass.DATE_TIME_PATTERN).withLocale(
            Locale.getDefault()
        ).withZone(ZoneId.from(ZoneOffset.UTC))
        val sampleDateString = formatter.format(sampleDate)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated 7 days ago", formattedDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in months`() {
        val sampleDate =
            LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant().minusSeconds(2634568)
        val formatter = DateTimeFormatter.ofPattern(FormatterClass.DATE_TIME_PATTERN).withLocale(
            Locale.getDefault()
        ).withZone(ZoneId.from(ZoneOffset.UTC))
        val sampleDateString = formatter.format(sampleDate)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated 1 months ago", formattedDate)
    }

    @Test
    fun `Check getUpdatedStatusWithGiveDate with delay in years`() {
        val sampleDate =
            LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant().minusSeconds(64023000)
        val formatter = DateTimeFormatter.ofPattern(FormatterClass.DATE_TIME_PATTERN).withLocale(
            Locale.getDefault()
        ).withZone(ZoneId.from(ZoneOffset.UTC))
        val sampleDateString = formatter.format(sampleDate)
        val formattedDate = FormatterClass.getUpdatedStatusWithGiveDate(sampleDateString)
        assertEquals("Updated 2 years ago", formattedDate)
    }
}