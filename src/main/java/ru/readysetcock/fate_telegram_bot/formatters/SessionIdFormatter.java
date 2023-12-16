package ru.readysetcock.fate_telegram_bot.formatters;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionIdFormatter {
    public static final String SESSION_ID_TAG = "sid";

    public static String formatSid(Update update) {
        String sid = "undefined";
        if (update.hasMessage()) {
            Message msg = update.getMessage();
            sid = "%s#%s".formatted(msg.getFrom().getUserName(), msg.getMessageId());
        } else if (update.hasCallbackQuery()) {
            CallbackQuery query = update.getCallbackQuery();
            sid = "%s#%s".formatted(query.getFrom().getUserName(), query.getMessage().getMessageId());
        }
        return sid;
    }

}
