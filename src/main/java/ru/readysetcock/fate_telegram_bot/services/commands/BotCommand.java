package ru.readysetcock.fate_telegram_bot.services.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.readysetcock.fate_telegram_bot.model.domain.KabbalisticNumber;

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
    ZODIACS("/zodiacs"),
    KABBALAH("/kabbalah");

    @Getter
    private final String commandText;

    @Override
    public String toString() {
        return commandText;
    }
}