package com.vaibhav.githubclone

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object FormatterClass {


    fun getUpdatedStatusWithGiveDate(lastUpdated: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = LocalDateTime.parse(lastUpdated, formatter)
        val milliseconds = date.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
        val timePassed = (LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant()
            .toEpochMilli() - milliseconds) / 1000

        var interval = timePassed / 31536000
        if (interval >= 1) {
            return "Updated $interval years ago";
        }
        interval = timePassed / 2592000;
        if (interval >= 1) {
            return "Updated $interval months ago";
        }
        interval = timePassed / 86400;
        if (interval == 1L)
            return "Updated yesterday"
        else if (interval > 1) {
            return "Updated $interval days ago";
        }
        interval = timePassed / 3600;
        if (interval >= 1) {
            return "Updated $interval hours ago";
        }
        interval = timePassed / 60;
        if (interval >= 1) {
            return "Updated $interval minutes ago";
        }
        return "Updated few seconds ago";
    }

    fun getNumberFormatted(number: String): String {
        val num = number.toInt()
        if (num > 1000000)
            return "${num / 1000000}M"
        else if (num > 1000)
            return "${num / 1000}K"
        return number
    }

    fun createFormattedStringForTextView(text: String, suffix: String): CharSequence? {
        val tempSpanString = SpannableString(getNumberFormatted(text))
        tempSpanString.setSpan(RelativeSizeSpan(1.35f), 0, tempSpanString.length, 0)
        tempSpanString.setSpan(ForegroundColorSpan(Color.BLACK), 0, tempSpanString.length, 0)
        tempSpanString.setSpan(StyleSpan(Typeface.BOLD), 0, tempSpanString.length, 0)
        return TextUtils.concat(tempSpanString, "\n", suffix)
    }
}