package ru.readysetcock.fate_telegram_bot.formatters;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateFormatter {
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("MM.dd");

    public static String formatToDayMonth(LocalDate date) {
        return date.format(FORMAT_DATE);
    }
}