package ru.readysetcock.fate_telegram_bot.messages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

/**
 * Класс для создания различных объектов-методов для взаимодействия с клиентом.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BotApiMethodFactory {

    /**
     * Создает обычное текстовое сообщение.
     *
     * @param chatId идентификатор чата
     * @param text   текст сообщения
     * @return объект для отправки клиенту
     */
    public static SendMessage textMessage(Long chatId, String text) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .parseMode(ParseMode.HTML)
                .text(text)
                .build();
    }

    /**
     * Создает новое сообщение с встроенной клавиатурой.
     *
     * @param chatId идентификатор чата
     * @param text   текст сообщения
     * @param markup разметка клавиатуры
     * @return объект для отправки клиенту
     */
    public static SendMessage inlineKeyboardMessage(Long chatId, String text, InlineKeyboardMarkup markup) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .parseMode(ParseMode.HTML)
                .replyMarkup(markup)
                .build();
    }

    /**
     * Создает сообщение с одним изображением и текстом.
     *
     * @param chatId  идентификатор чата
     * @param fileId  идентификатор файла в телеграме
     * @param text    текст сообщения
     * @param spoiler использовать спойлер
     * @return объект для отправки клиенту
     */
    public static SendPhoto messageWithPhoto(Long chatId, String fileId, String text, boolean spoiler, InlineKeyboardMarkup markup) {
        return SendPhoto.builder()
                .chatId(chatId)
                .caption(text)
                .hasSpoiler(spoiler)
                .photo(new InputFile(fileId))
                .replyMarkup(markup)
                .parseMode(ParseMode.HTML)
                .build();
    }

    public static SendPhoto messageWithPhoto(Long chatId, String fileId, String text, InlineKeyboardMarkup markup) {
        return messageWithPhoto(chatId, fileId, text, false, markup);
    }

    public static SendPhoto messageWithPhoto(Long chatId, String fileId, String text) {
        return messageWithPhoto(chatId, fileId, text, false, null);
    }
    public static SendPhoto messageWithPhoto(Long chatId, String fileId, boolean spoiler) {
        return messageWithPhoto(chatId, fileId, "", spoiler, null);
    }

    /**
     * Создает сообщение с группой фотографий.
     * Группу можно создать с помощью PhotoGroupBuilder
     * Текст после группы нужно отправлять отдельно
     *
     * @param chatId идентификатор чата
     * @param images группа изображений
     * @return объект для отправки клиенту
     * @see PhotoGroupBuilder
     */
    public static SendMediaGroup messageWithPhotoGroup(Long chatId, List<InputMediaPhoto> images) {
        return SendMediaGroup.builder()
                .chatId(chatId)
                .medias(images.stream().map(InputMedia.class::cast).toList())
                .build();

    }

    /**
     * Изменяет существующее сообщение.
     *
     * @param chatId       идентификатор чата
     * @param messageId    идентификатор сообщения
     * @param editedText   новый текст сообщения
     * @param editedMarkup новая разметка клавиатуры
     * @return объект для отправки клиенту
     */
    public static EditMessageText messageEdit(Long chatId, Integer messageId, String editedText, InlineKeyboardMarkup editedMarkup) {
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .parseMode(ParseMode.HTML)
                .text(editedText)
                .replyMarkup(editedMarkup)
                .build();
    }

    /**
     * Завершает ожидание клиента после нажатия на кнопку.
     * При этом не отправляет пользователю никакой информации.
     * Нужно отправлять всегда при нажатии на кнопку, если клавиатура не меняется.
     *
     * @param queryId идентификатор запроса
     * @return объект для отправки клиенту
     */
    public static AnswerCallbackQuery callbackQueryAnswer(String queryId) {
        return callbackQueryAnswer(queryId, null, false);
    }

    /**
     * Завершает ожидание клиента после нажатия на кнопку и
     * отправляет пользователю уведомление с текстом.
     *
     * @param queryId идентификатор запроса
     * @param text    текст уведомления
     * @param alert   флаг отправки уведомления об ошибке
     * @return объект для отправки клиенту
     */
    public static AnswerCallbackQuery callbackQueryAnswer(String queryId, String text, boolean alert) {
        return AnswerCallbackQuery.builder()
                .callbackQueryId(queryId)
                .text(text)
                .showAlert(alert)
                .build();
    }

    /**
     * Удаляет существующее сообщение.
     *
     * @param chatId    идентификатор чата
     * @param messageId идентификатор сообщения
     * @return объект для отправки клиенту
     */
    public static DeleteMessage deleteMessage(Long chatId, Integer messageId) {
        return DeleteMessage.builder()
                .chatId(chatId)
                .messageId(messageId)
                .build();
    }
}