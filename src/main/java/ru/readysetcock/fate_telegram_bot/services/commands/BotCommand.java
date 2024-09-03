package ru.readysetcock.fate_telegram_bot.services.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Список доступных комманд бота.
 */
@AllArgsConstructor
public enum BotCommand {
    MENU("/menu"),
    START("/start"),
    HELP("/help"),
    TAROS("/taros"),
    CARD_OF_THE_DAY("/card_of_the_day"),
    DIVINATION("/div"),
    TARO_LAYOUTS("/taro_layouts"),
    CHAKRAS("/chakras"),
    ARCANES("/arcanes"),
    CATALOGUE("/catalogue"),
    STONES("/stones"),
    ZODIACS("/zodiacs"),
    KABBALAH("/kabbalah");

    @Getter
    private final String commandText;

    @Override
    public String toString() {
        return commandText;
    }
}