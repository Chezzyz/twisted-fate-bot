package ru.readysetcock.fate_telegram_bot.formatters;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateFormatter {
    public static final DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("MM.dd");

    public static String format(LocalDate date, DateTimeFormatter formatter) {
        return date.format(formatter);
    }
}