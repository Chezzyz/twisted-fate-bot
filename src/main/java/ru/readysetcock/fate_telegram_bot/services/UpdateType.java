package ru.readysetcock.fate_telegram_bot.services;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.readysetcock.fate_telegram_bot.services.domain.UserService;

public enum UpdateType {
    MESSAGE,
    CALLBACK_QUERY,
    NO_MESSAGE_NO_QUERY,
    COMMAND,
    STATE;

    public static UpdateType getType(Update update, UserService service) {
        if (update.hasCallbackQuery()) {
            return CALLBACK_QUERY;
        } else if (update.hasMessage()) {
            if (update.getMessage().isCommand()) {
                return COMMAND;
            } else if (service.hasState(update.getMessage().getChatId())) {
                return STATE;
            } else {
                return MESSAGE;
            }
        } else {
            return NO_MESSAGE_NO_QUERY;
        }
    }
}