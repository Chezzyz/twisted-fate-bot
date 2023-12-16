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
    DIVINATION("/divination"),
    CHAKRAS("/chakras"),
    ARCANES("/arcanes"),
    CATALOGUE("/catalogue"),
    STONES("/stones"),
    ZODIACS("/zodiacs");




    @Getter
    private final String commandText;
}