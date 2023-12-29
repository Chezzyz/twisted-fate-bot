package ru.readysetcock.fate_telegram_bot.services.functions.divination;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.readysetcock.fate_telegram_bot.messages.Response;

public interface DivinationSubprocessor {
    DivinationType getDivinationType();

    Response process(CallbackQuery query);
}
