package com.example.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimePrettierFormatter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String doFormat(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

}
