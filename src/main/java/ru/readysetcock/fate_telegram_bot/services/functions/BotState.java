package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BotState {
    QUESTION("Введите свой вопрос");

    private final String text;
}
