package ru.readysetcock.fate_telegram_bot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;
import ru.readysetcock.fate_telegram_bot.services.domain.UserService;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunctionProcessor;

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
    private final UserService userService;
    private final UserStateController userStateController;
    private final Map<String, BotCommandProcessor> commandProcessorsMap;
    private final Map<String, BotFunctionProcessor> functionProcessorsMap;

    public BotServicesController(List<BotCommandProcessor> commandProcessors, List<BotFunctionProcessor> functionProcessors, UserService userService, UserStateController userStateController) {
        commandProcessorsMap = commandProcessors.stream()
                .collect(Collectors.toMap(processor -> processor.getCommand().getCommandText(), Function.identity()));
        functionProcessorsMap = functionProcessors.stream()
                .collect(Collectors.toMap(processor -> processor.getFunction().getFunctionName(), Function.identity()));
        this.userService = userService;
        this.userStateController = userStateController;
    }

    public Response getResponse(Update update) {
        Message message = update.getMessage();
        try {
            return switch (UpdateType.getType(update, userService)) {
                case STATE -> userStateController.processWithState(update);
                case COMMAND -> processWithCommand(message);
                case CALLBACK_QUERY -> processWithCallbackQuery(update.getCallbackQuery());
                case MESSAGE -> {
                    log.warn("Не смогли обработать сообщение - {}", message.getText());
                    yield createDefaultMessage(message.getChatId());
                }
                case NO_MESSAGE_NO_QUERY -> {
                    log.warn("Пришло сообщение без message и callback - {}", update);
                    yield new Response();
                }
            };
        } catch (Exception e) {
            log.error("Произошла ошибка - {}", e,e);
            return createServerErrorMessage(update.getMessage().getChatId());
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

    private Response createUnknownCommandMessage(Long chatId) {
        return new Response(BotApiMethodFactory.textMessage(chatId, "Такая команда мне неизвестна \uD83E\uDD28"));
    }

    private Response createNotImplementedCallbackQueryAnswer(String queryId) {
        return new Response(BotApiMethodFactory.callbackQueryAnswer(queryId, "Эта функция еще в разработке", false));
    }

    private Response createDefaultMessage(Long chatId) {
        return new Response(BotApiMethodFactory.textMessage(chatId, "Используй /menu для вызова меню или /help для списка команд"));
    }

    private Response createServerErrorMessage(Long chatId) {
        return new Response(BotApiMethodFactory.textMessage(chatId, "Извините, на сервере произошла ошибка 😣"));
    }
}