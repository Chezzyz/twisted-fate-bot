package ru.readysetcock.fate_telegram_bot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.readysetcock.fate_telegram_bot.formatters.SessionIdFormatter;
import ru.readysetcock.fate_telegram_bot.messages.MessageSender;
import ru.readysetcock.fate_telegram_bot.services.BotServicesController;

@Component
@Slf4j
public class TelegramController extends TelegramLongPollingBot {
    private final String botName;
    private final BotServicesController botServicesController;


    public TelegramController(@Value("${bot.api.token}") String botToken, @Value("${bot.name}") String botName, BotServicesController botServicesController) {
        super(botToken);
        this.botName = botName;
        this.botServicesController = botServicesController;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        MDC.put(SessionIdFormatter.SESSION_ID_TAG, SessionIdFormatter.formatSid(update));

        MessageSender.sendResponse(botServicesController.getResponse(update), this);

        MDC.remove(SessionIdFormatter.SESSION_ID_TAG);
    }

}
