package ru.readysetcock.fate_telegram_bot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.User;
import ru.readysetcock.fate_telegram_bot.repository.UserRepository;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunction;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunctionProcessor;
import ru.readysetcock.fate_telegram_bot.services.functions.BotState;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Контроллер, распределяющий входящие запросы в сервисы.
 */
@Service
@Slf4j
public class BotServicesController {
    private final UserRepository userRepository;
    private final Map<String, BotCommandProcessor> commandProcessorsMap;
    private final Map<String, BotFunctionProcessor> functionProcessorsMap;

    public BotServicesController(List<BotCommandProcessor> commandProcessors, List<BotFunctionProcessor> functionProcessors, UserRepository userRepository) {
        commandProcessorsMap = commandProcessors.stream()
                .collect(Collectors.toMap(processor -> processor.getCommand().getCommandText(), Function.identity()));
        functionProcessorsMap = functionProcessors.stream()
                .collect(Collectors.toMap(processor -> processor.getFunction().getFunctionName(), Function.identity()));
        this.userRepository = userRepository;
    }

    public Response getResponse(Update update) {
        if (update.hasMessage() && update.getMessage().isCommand()) {
            return processWithCommand(update.getMessage());
        } else if (update.hasCallbackQuery()) {
            return processWithCallbackQuery(update.getCallbackQuery());
        } else if (update.hasMessage() && userRepository.existsByTgUserIdAndStateIsNotNull(update.getMessage().getChatId())) {
            return processWithState(update);
        } else if (update.hasMessage()) {
            log.info("Получил хуй пойми че");
            return createDefaultMessage(update.getMessage().getChatId());
        } else {
            log.warn("Пришло сообщение без message и callback - {}", update);
            return new Response();
        }
    }

    private Response processWithCommand(Message message) {
        String command = message.getText();
        BotCommandProcessor processor = commandProcessorsMap.get(command);
        if (processor != null) {
            log.info("Получил команду '{}', отправляю в {}", command, processor.getClass().getSimpleName());
            return processor.process(message);
        } else {
            log.warn("Получил неизвестную команду '{}'", command);
            return createUnknownCommandMessage(message.getChatId());
        }
    }

    private Response processWithCallbackQuery(CallbackQuery query) {
        String data = query.getData();
        String functionName = data.split("/")[0];
        BotFunctionProcessor processor = functionProcessorsMap.get(functionName);
        if (processor != null) {
            log.info("Получил callback '{}', отправляю в {}", data, processor.getClass().getSimpleName());
            return processor.process(query);
        } else {
            log.warn("Получил callback неизвестной функции '{}'", data);
            return createNotImplementedCallbackQueryAnswer(query.getId());
        }
    }

    private Response processWithState(Update update) {
        BotState currentState = BotState.values()[userRepository.findByTgUserId(update.getMessage().getChatId()).getState()];
        return switch (currentState) {
            case QUESTION -> processQuestionState(update);
        };
    }

    private Response processQuestionState(Update update) {
        String question = update.getMessage().getText();
        User user = userRepository.findByTgUserId(update.getMessage().getChatId());
        user.setState(null);
        userRepository.save(user);
        return new Response(BotApiMethodFactory.inlineKeyboardMessage(update.getMessage().getChatId(), "Ваш вопрос: %s".formatted(question),
                InlineKeyboardBuilder.createKeyboardOf(InlineKeyboardBuilder
                        .rowOf(InlineKeyboardBuilder.button("Да", "\uD83D\uDC4D", BotFunction.KABBALAH.getFunctionName().concat("/question")),
                                InlineKeyboardBuilder.button("Нет", "\uD83D\uDC4E", BotFunction.KABBALAH.getFunctionName().concat("/div"))))));
    }

    private Response createUnknownCommandMessage(Long chatId) {
        return new Response(BotApiMethodFactory.textMessage(chatId, "Такая команда мне неизвестна \uD83E\uDD28"));
    }

    private Response createNotImplementedCallbackQueryAnswer(String queryId) {
        return new Response(BotApiMethodFactory.callbackQueryAnswer(queryId, "Эта функция еще в разработке", false));
    }

    private Response createDefaultMessage(Long chatId) {
        return new Response(BotApiMethodFactory.textMessage(chatId, "Используй /menu для вызова меню или /help для списка команд"));
    }
}