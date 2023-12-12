package ru.readysetcock.fate_telegram_bot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunctionProcessor;

import java.io.Serializable;
import java.util.Collections;
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
    private final Map<String, BotCommandProcessor> commandProcessorsMap;
    private final Map<String, BotFunctionProcessor> functionProcessorsMap;

    public BotServicesController(List<BotCommandProcessor> commandProcessors, List<BotFunctionProcessor> functionProcessors) {
        commandProcessorsMap = commandProcessors.stream()
                .collect(Collectors.toMap(processor -> processor.getCommand().getCommandText(), Function.identity()));
        functionProcessorsMap = functionProcessors.stream()
                .collect(Collectors.toMap(processor -> processor.getFunction().getFunctionName(), Function.identity()));
    }

    public List<BotApiMethod<? extends Serializable>> getResponse(Update update) {
        if (update.hasMessage() && update.getMessage().isCommand()) {
            return processWithCommand(update.getMessage());
        } else if (update.hasCallbackQuery()) {
            return processWithCallbackQuery(update.getCallbackQuery());
        } else {
            log.info("Получил хуй пойми че");
            return createDefaultMessage(update.getMessage().getChatId());
        }
    }

    private List<BotApiMethod<? extends Serializable>> processWithCommand(Message message) {
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

    private List<BotApiMethod<? extends Serializable>> processWithCallbackQuery(CallbackQuery query) {
        String data = query.getData();
        String functionName = data.split("/")[0];
        BotFunctionProcessor processor = functionProcessorsMap.get(functionName);
        if (processor != null) {
            log.info("Получил callback '{}', отправляю в {}", data, processor.getClass().getSimpleName());
            return processor.process(query);
        } else {
            log.warn("Получил callback неизвестной функции '{}'", data);
            return createEmptyCallbackQueryAnswer(query.getId());
        }
    }

    private List<BotApiMethod<? extends Serializable>> createUnknownCommandMessage(Long chatId) {
        return Collections.singletonList(BotApiMethodFactory.textMessage(chatId, "Такая команда мне неизвестна \uD83E\uDD28"));
    }

    private List<BotApiMethod<? extends Serializable>> createEmptyCallbackQueryAnswer(String queryId) {
        return Collections.singletonList(BotApiMethodFactory.callbackQueryAnswer(queryId));
    }

    private List<BotApiMethod<? extends Serializable>> createDefaultMessage(Long chatId) {
        return Collections.singletonList(BotApiMethodFactory.textMessage(chatId,
                "Используй /menu для вызова меню или /help для списка команд"));
    }
}
