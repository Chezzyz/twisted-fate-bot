package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Список доступных функций.
 */
@AllArgsConstructor
public enum BotFunction {
    MENU("menu"),
    DIVINATION("div"),
    CHAKRAS("chakras"),
    ARCANES("arcanes"),
    CATALOGUE("catalogue"),
    STONES("stones"),
    ZODIAC("zodiacs"),
    TAROS("taros"),
    TARO_LAYOUTS("taro_layouts"),
    CARD_OF_THE_DAY("card_of_the_day"),
    KABBALAH("kabbalah");

    @Getter
    private final String functionName;

    @Override
    public String toString() {
        return functionName;
    }
}
