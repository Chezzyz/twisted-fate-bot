package ru.readysetcock.fate_telegram_bot.messages;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class MessageCreator {

    public SendMessage createTextMessage(Long chatId, String text) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(text).build();
    }

}
