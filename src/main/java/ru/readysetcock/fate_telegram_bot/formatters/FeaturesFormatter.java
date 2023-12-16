package ru.readysetcock.fate_telegram_bot.formatters;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeaturesFormatter {

    public static String formatPrettyFeatures(@NotNull String[] features) {
        return Arrays.stream(features).map("⭐ %s%n"::formatted).collect(Collectors.joining());
    }

}
