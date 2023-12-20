package ru.readysetcock.fate_telegram_bot.services.functions.divination;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Список тематик вопросов для гадания
 */
@AllArgsConstructor
public enum DivinationTopic {
    LOVE("love"),
    JOB("job"),
    SPIRIT("spirit"),
    HEALTH("health"),
    DECISION("decision"),
    GROWTH("growth"),
    INSIGNIFICANT("ins");

    @Getter
    private final String functionName;

    @Override
    public String toString() {
        return functionName;
    }
}
