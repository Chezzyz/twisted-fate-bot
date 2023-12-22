package ru.readysetcock.fate_telegram_bot.formatters;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LayoutFormatter {
    private static final String[] EMOJIS = {
            "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣",
            "6️⃣", "7️⃣", "8️⃣", "9️⃣", "🔟"
    };
    public static String formatLayoutPositions(@NotNull String[] positions){
        return Arrays.stream(positions)
                .map(LayoutFormatter::mapPosition)
                .collect(Collectors.joining("\n")) + "\n";
    }

    private static String mapPosition(String position) {
        int index = position.indexOf('.');
        if (index != -1) {
            int digit = Integer.parseInt(position.substring(0, index));
            if (digit >= 1 && digit <= EMOJIS.length) {
                return EMOJIS[digit - 1] + position.substring(index + 1);
            }
        }
        return position;
    }
}
