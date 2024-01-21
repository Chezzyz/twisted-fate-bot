package ru.readysetcock.fate_telegram_bot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.User;
import ru.readysetcock.fate_telegram_bot.services.domain.UserService;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunction;
import ru.readysetcock.fate_telegram_bot.services.functions.BotState;

@Service
@RequiredArgsConstructor
public class UserStateController {
    private final UserService userService;

    public Response processWithState(Update update) {
        BotState currentState = BotState.getByStateName(userService.findByUserId(update.getMessage().getChatId()).getState());
        return switch (currentState) {
            case QUESTION -> processQuestionState(update);
        };
    }

    private Response processQuestionState(Update update) {
        String question = update.getMessage().getText();
        User user = userService.findByUserId(update.getMessage().getChatId());
        user.setState(null);
        userService.save(user);
        return new Response(BotApiMethodFactory.inlineKeyboardMessage(update.getMessage().getChatId(), "Ваш вопрос: %s".formatted(question),
                InlineKeyboardBuilder.createKeyboardOf(InlineKeyboardBuilder
                        .rowOf(InlineKeyboardBuilder.button("Да", "\uD83D\uDC4D", BotFunction.KABBALAH.getFunctionName().concat("/question")),
                                InlineKeyboardBuilder.button("Нет", "\uD83D\uDC4E", BotFunction.KABBALAH.getFunctionName().concat("/div"))))));
    }
}