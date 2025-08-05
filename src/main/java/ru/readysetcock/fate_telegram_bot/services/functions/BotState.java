package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BotState {
    KABBALAH_QUESTION("kabbalah question", "Введите свой вопрос", ""),
    TARO_QUESTION("taro question", "Введите свой вопрос", "taro question '%s'");

    private final String stateName;
    private final String text;
    private final String contextPattern;

    public static BotState getByUserState(String userState) {
        for (BotState botState : BotState.values()) {
            if (userState.toLowerCase().contains(botState.stateName)) {
                return botState;
            }
        }
        return null;
    }

    public static String retrieveData(String userState) {
        return userState.split("'")[1];
    }
}
