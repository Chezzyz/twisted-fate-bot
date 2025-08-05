package ru.readysetcock.fate_telegram_bot.services.functions;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.readysetcock.fate_telegram_bot.messages.Response;

import java.util.Set;

public interface BotStateProcessor {
    Response processState(Update update, String data);

    Set<BotState> getStates();
}
