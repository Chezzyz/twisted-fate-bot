package ru.readysetcock.fate_telegram_bot.formatters;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LayoutFormatter {
    private static final Map<String, String> EMOJI_MAP = Map.ofEntries(
            Map.entry("S", "🆂"),
            Map.entry("1", "1️⃣"),
            Map.entry("2", "2️⃣"),
            Map.entry("3", "3️⃣"),
            Map.entry("4", "4️⃣"),
            Map.entry("5", "5️⃣"),
            Map.entry("6", "6️⃣"),
            Map.entry("7", "7️⃣"),
            Map.entry("8", "8️⃣"),
            Map.entry("9", "9️⃣"),
            Map.entry("10", "🔟")
    );

    public static String formatLayoutPositions(@NotNull String[] positions) {
        return Arrays.stream(positions)
                .map(pos -> EMOJI_MAP.get(pos.split("\\.")[0]) + pos.split("\\.")[1])
                .collect(Collectors.joining("\n")) + "\n";
    }
    public static String getEmojiOfNumber(String key){
        return EMOJI_MAP.get(key);
    }
}