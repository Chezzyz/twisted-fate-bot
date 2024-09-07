package ru.readysetcock.fate_telegram_bot.services.commands;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunction;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunctionProcessor;

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
    public Response process(Message message) {
        return new Response(BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(), "Выберите функцию", createMenuKeyboard()));
    }

    @Override
    public Response process(CallbackQuery query) {
        Message message = query.getMessage();
        if(query.getData().contains("/new")){
            return new Response(BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(), "Выберите функцию", createMenuKeyboard()));
        } else {
            return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Выберите функцию", createMenuKeyboard()));
        }
    }

    private InlineKeyboardMarkup createMenuKeyboard() {
        return InlineKeyboardBuilder.createKeyboardOf(
                rowOf(button("Гадание", "\uD83D\uDD2E", "div")),
                rowOf(button("Каббала", "\uD83D\uDCD6", BotFunction.KABBALAH.toString())),
                rowOf(button("\uD83D\uDD12 Чакроанализ", "☸", "chakras")),
                rowOf(button("\uD83D\uDD12 Расчет аркан", "\uD83C\uDCCF", "arcanes")),
                rowOf(button("Справочники", "\uD83D\uDCDA", "catalogue")),
                rowOf(button("Карта дня", "🔮📆", BotFunction.CARD_OF_THE_DAY.toString()))
        );
    }
}
