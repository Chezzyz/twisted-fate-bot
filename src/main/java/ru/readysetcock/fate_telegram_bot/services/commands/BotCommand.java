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
    HELP("/help");

    @Getter
    private final String commandText;
}
