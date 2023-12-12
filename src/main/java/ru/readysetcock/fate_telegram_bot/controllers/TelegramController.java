package ru.readysetcock.fate_telegram_bot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.readysetcock.fate_telegram_bot.services.BotServicesController;

import java.io.Serializable;
import java.util.List;

@Component
@Slf4j
public class TelegramController extends TelegramLongPollingBot {
    private final String botName;
    private final BotServicesController botServicesController;
    public static final String SESSION_ID_TAG = "sid";

    public TelegramController(@Value("${bot.api.token}") String botToken, @Value("${bot.name}") String botName, BotServicesController botServicesController) {
        super(botToken);
        this.botName = botName;
        this.botServicesController = botServicesController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        MDC.put(SESSION_ID_TAG, getSid(update));

        List<BotApiMethod<? extends Serializable>> responses = botServicesController.getResponse(update);
        responses.forEach(this::send);

        MDC.remove(SESSION_ID_TAG);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    private String getSid(Update update) {
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

    private void send(BotApiMethod<? extends Serializable> method) {
        try {
            log.info("Отправляю метод {}", method.getClass().getSimpleName());
            super.execute(method);
        } catch (TelegramApiException e) {
            log.error("При вызове execute(method) произошла ошибка:", e);
        }
    }

}
