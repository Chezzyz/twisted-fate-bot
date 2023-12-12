package ru.readysetcock.fate_telegram_bot.services.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;
import java.util.List;

/**
 * Интерфейс обработчика комманд бота.
 */
public interface BotCommandProcessor {
    /**
     * Команда обработчика.
     *
     * @return команда из списка
     */
    BotCommand getCommand();

    /**
     * Метод обработки команды.
     * Должен возвращать объекты, созданные с помощью BotApiMethodFactory.
     *
     * @param message объект сообщения от клиента
     * @return объект для отправки клиенту
     * @see ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory BotApiMethodFactory
     */
    List<BotApiMethod<? extends Serializable>> process(Message message);
}
