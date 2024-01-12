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
    CARDOFTHEDAY("/card_of_the_day"),
    DIVINATION("/div"),
    TAROLAYOUTS("/taro_layouts"),
    CHAKRAS("/chakras"),
    ARCANES("/arcanes"),
    CATALOGUE("/catalogue"),
    STONES("/stones"),
    ZODIACS("/zodiacs");

    @Getter
    private final String commandText;

    @Override
    public String toString() {
        return commandText;
    }
}