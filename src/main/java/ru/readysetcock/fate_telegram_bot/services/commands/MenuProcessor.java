package ru.readysetcock.fate_telegram_bot.services.commands;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunction;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunctionProcessor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;

@Service
public class MenuProcessor implements BotCommandProcessor, BotFunctionProcessor {
    @Override
    public BotCommand getCommand() {
        return BotCommand.MENU;
    }

    @Override
    public BotFunction getFunction() {
        return BotFunction.MENU;
    }

    @Override
    public List<BotApiMethod<? extends Serializable>> process(Message message) {
        return Collections.singletonList(BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(),
                "Выберите функцию", createMenuKeyboard()));
    }

    @Override
    public List<BotApiMethod<? extends Serializable>> process(CallbackQuery query) {
        Message message = query.getMessage();
        return Collections.singletonList(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(),
                "Выберите функцию", createMenuKeyboard()));
    }

    private static InlineKeyboardMarkup createMenuKeyboard() {
        return InlineKeyboardBuilder.createKeyboardOf(
                rowOf(button("TODO Гадание", "\uD83D\uDD2E", "divination")),
                rowOf(button("TODO Чакры", "☸", "chakras")),
                rowOf(button("TODO Расчет аркан", "\uD83C\uDCCF", "arcanes")),
                rowOf(button("Справочники", "\uD83D\uDCDA", "catalogue"))
        );
    }
}
