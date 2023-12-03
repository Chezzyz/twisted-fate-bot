package ru.readysetcock.fate_telegram_bot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.readysetcock.fate_telegram_bot.messages.MessageCreator;

import java.io.Serializable;

@Component
@Slf4j
public class TelegramController extends TelegramLongPollingBot {
    private final String botName;
    private final MessageCreator messageCreator;

    public TelegramController(@Value("${bot.api.token}") String botToken, @Value("${bot.name}") String botName, MessageCreator messageCreator) {
        super(botToken);
        this.botName = botName;
        this.messageCreator = messageCreator;
    }

    @Override
    public void onUpdateReceived(Update update) {
        send(messageCreator.createTextMessage(update.getMessage().getChatId(), "Hello World!"));
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    private void send(BotApiMethod<? extends Serializable> method) {
        try {
            super.execute(method);
        } catch (TelegramApiException e) {
            log.error("При вызове execute() произошла ошибка", e);
        }
    }

}
