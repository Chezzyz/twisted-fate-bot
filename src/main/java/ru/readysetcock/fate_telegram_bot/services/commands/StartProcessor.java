package ru.readysetcock.fate_telegram_bot.services.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.Response;

@Service
@RequiredArgsConstructor
public class StartProcessor implements BotCommandProcessor {
    @Override
    public BotCommand getCommand() {
        return BotCommand.START;
    }

    @Override
    public Response process(Message message) {
        return new Response(startMessage(message));
    }

    private SendMessage startMessage(Message message) {
        String formattedMessage = """
                Привет, %s!
                
                🔮 TwistedFateBot предлагает различные функции эзотерического мира:
                
                \uD83E\uDEAC Гадание, чакроанализ, совместимость по знакам зодиака и многое другое!
                
                ℹ Чтобы узнать больше о функциях бота воспользуйтесь
                командами /help и /menu.
                """.formatted(message.getFrom().getFirstName());
        return BotApiMethodFactory.textMessage(message.getChatId(), formattedMessage);
    }
}
