package com.vaibhav.githubclone

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

object FormatterClass {


    fun getUpdatedStatusWithGiveDate(lastUpdated: String): String{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = LocalDateTime.parse(lastUpdated,formatter)
        val milliseconds = date.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
        val timePassed = (LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant().toEpochMilli() - milliseconds) / 1000

        var interval = timePassed / 31536000
        if (interval >= 1) {
            return "Updated $interval years ago";
        }
        interval = timePassed / 2592000;
        if (interval >= 1) {
            return "Updated $interval months ago";
        }
        interval = timePassed / 86400;
        if(interval == 1L)
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

}