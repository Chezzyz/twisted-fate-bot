package ru.readysetcock.fate_telegram_bot.services.functions;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;

@Service
public class CatalogueProcessor implements BotFunctionProcessor {
    @Override
    public BotFunction getFunction() {
        return BotFunction.CATALOGUE;
    }

    @Override
    public Response process(CallbackQuery query) {
        InlineKeyboardMarkup keyboard = InlineKeyboardBuilder.createKeyboardOf(
                rowOf(button("TODO Карты таро", "\uD83C\uDCCF", "taros")),
                rowOf(button("TODO Знаки зодиака", "⭐", "zodiacs")),
                rowOf(button("TODO Руны", "\uD83C\uDC04", "runes")),
                rowOf(button("TODO Драгоценные камни", "\uD83D\uDC8E", "stones")),
                rowOf(button("⬅ Назад", "menu"))
        );
        Message message = query.getMessage();

        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Выберите справочник", keyboard));
    }
}
