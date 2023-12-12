package ru.readysetcock.fate_telegram_bot.services.functions;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.io.Serializable;
import java.util.List;

/**
 * Интерфейс обработки данных для функций бота.
 */
public interface BotFunctionProcessor {
    /**
     * Функция обработчика.
     *
     * @return функцию из списка
     */
    BotFunction getFunction();

    /**
     * Метод обработки команды.
     * Должен возвращать объекты, созданные с помощью BotApiMethodFactory.
     *
     * @param query запрос от нажатия кнопки
     * @return объект для отправки клиенту
     * @see ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory BotApiMethodFactory
     */
    List<BotApiMethod<? extends Serializable>> process(CallbackQuery query);
}
