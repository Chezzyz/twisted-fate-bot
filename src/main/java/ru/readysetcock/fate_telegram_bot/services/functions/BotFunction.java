package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Список доступных функций.
 */
@AllArgsConstructor
public enum BotFunction {
    MENU("menu"),
    DIVINATION("divination"),
    CHAKRAS("chakras"),
    ARCANES("arcanes"),
    CATALOGUE("catalogue"),
    TAROS("taros");

    @Getter
    private final String functionName;
}
