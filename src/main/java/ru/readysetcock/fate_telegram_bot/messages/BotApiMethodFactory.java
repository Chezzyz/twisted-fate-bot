package ru.readysetcock.fate_telegram_bot.messages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

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
                .replyMarkup(markup)
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

}
