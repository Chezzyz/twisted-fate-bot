package ru.readysetcock.fate_telegram_bot.messages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageSender {
    private static final String EXECUTE_ERROR_MESSAGE = "При вызове execute(method) произошла ошибка:";

    public static void sendResponse(Response response, AbsSender sender) {
        if (response.photo() != null) {
            sendPhoto(response.photo(), sender);
        }
        if (response.photos() != null) {
            sendPhotoGroup(response.photos(), sender);
        }
        if (response.methods() != null) {
            response.methods().forEach(method -> sendMethod(method, sender));
        }
    }

    private static void sendMethod(BotApiMethod<? extends Serializable> method, AbsSender sender) {
        try {
            log.info("Отправляю метод {}", method.getClass().getSimpleName());
            sender.execute(method);
        } catch (TelegramApiException e) {
            log.error(EXECUTE_ERROR_MESSAGE, e);
        }
    }

    private static void sendPhoto(SendPhoto method, AbsSender sender) {
        try {
            log.info("Отправляю метод SendPhoto");
            sender.execute(method);
        } catch (TelegramApiException e) {
            log.error(EXECUTE_ERROR_MESSAGE, e);
        }
    }

    private static void sendPhotoGroup(SendMediaGroup method, AbsSender sender) {
        try {
            log.info("Отправляю метод SendMediaGroup");
            sender.execute(method);
        } catch (TelegramApiException e) {
            log.error(EXECUTE_ERROR_MESSAGE, e);
        }
    }

}
