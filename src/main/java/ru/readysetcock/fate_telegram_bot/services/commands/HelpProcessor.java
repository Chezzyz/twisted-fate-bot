package ru.readysetcock.fate_telegram_bot.services.commands;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.Response;

@Service
public class HelpProcessor implements BotCommandProcessor {
    @Override
    public BotCommand getCommand() {
        return BotCommand.HELP;
    }

    @Override
    public Response process(Message message) {
        return new Response(helpMessage(message));
    }

    private SendMessage helpMessage(Message message) {
        String formattedMessage = """
                🔮 TwistedFateBot предлагает различные функции эзотерического мира:
                - Главное меню со всеми функциями: /menu
                - Гадание разными способами: /divination
                - Расчет аркан: /arcanes
                - Чакроанализ : /chakras
                - Совместимость по знакам зодиака: /compatibility
                - Различные справочники: /taros, /zodiacs, /runes, /stones, /taro_layouts
                """;
        return BotApiMethodFactory.textMessage(message.getChatId(), formattedMessage);
    }
}
