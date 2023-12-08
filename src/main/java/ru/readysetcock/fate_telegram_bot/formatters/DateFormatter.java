package ru.readysetcock.fate_telegram_bot.formatters;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateFormatter {
   private static final DateTimeFormatter DAY_MONTH_FORMATTER = DateTimeFormatter.ofPattern("MM.dd");

   public static String dayMonthFormatter(LocalDate date) {
      return date.format(DAY_MONTH_FORMATTER);
   }
}