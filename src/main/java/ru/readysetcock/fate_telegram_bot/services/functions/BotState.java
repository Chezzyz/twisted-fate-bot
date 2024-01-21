package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BotState {
    QUESTION("question", "Введите свой вопрос");

    private final String stateName;
    private final String text;

    public static BotState getByStateName(String stateName) {
        for (BotState botState : BotState.values()) {
            if (botState.getStateName().equalsIgnoreCase(stateName)) {
                return botState;
            }
        }
        return null;
    }
}
