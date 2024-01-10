package ru.readysetcock.fate_telegram_bot.services.functions.divination;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommand;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunction;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunctionProcessor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;
import static ru.readysetcock.fate_telegram_bot.services.functions.BotFunction.DIVINATION;

@Service
@Slf4j
public class DivinationProcessor implements BotFunctionProcessor, BotCommandProcessor {
    private final Map<String, DivinationSubprocessor> subprocessorsMap;

    public DivinationProcessor(List<DivinationSubprocessor> divinationSubprocessors) {
        subprocessorsMap = divinationSubprocessors.stream()
                .collect(Collectors.toMap(subprocessor -> subprocessor.getDivinationType().getFunctionName(), Function.identity()));
    }

    @Override
    public BotCommand getCommand() {
        return BotCommand.DIVINATION;
    }

    @Override
    public BotFunction getFunction() {
        return DIVINATION;
    }

    @Override
    public Response process(Message message) {
        return sendKeyboardOfDivTypes(message);
    }

    @Override
    public Response process(CallbackQuery query) {
        String data = query.getData();
        if (data.equals(DIVINATION.toString())) {
            return editToButtonsWithDivTypes(query);
        } else if (data.startsWith(DIVINATION + "/")) {
            String subprocessorName = data.split("/")[1];
            DivinationSubprocessor subprocessor = subprocessorsMap.get(subprocessorName);
            if (subprocessor != null) {
                log.info("Получил callback '{}', отправляю в {}", data, subprocessor.getClass().getSimpleName());
                return subprocessor.process(query);
            } else {
                log.warn("Получил callback неизвестной функции '{}'", data);
                return createNotImplementedCallbackQueryAnswer(query.getId());
            }
        }
        return new Response();
    }

    private Response sendKeyboardOfDivTypes(Message message) {
        return new Response(BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(), "Выберите тип гадания", getDivTypesKeyboard()));
    }

    private Response editToButtonsWithDivTypes(CallbackQuery query) {
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Выберите тип гадания", getDivTypesKeyboard()));
    }

    private InlineKeyboardMarkup getDivTypesKeyboard() {
        return InlineKeyboardBuilder.createKeyboardOf(
                rowOf(button("Карты таро", "\uD83D\uDD2E", "%s/%s".formatted(BotFunction.DIVINATION, DivinationType.TARO))),
                rowOf(button("\uD83D\uDD12 Игральные карты", "\uD83C\uDCCF", "%s/%s".formatted(BotFunction.DIVINATION, DivinationType.GAMECARDS))),
                rowOf(button("\uD83D\uDD12 Гадание на рунах", "\uD83C\uDC04", "%s/%s".formatted(DIVINATION, DivinationType.RUNES))),
                rowOf(button("\uD83D\uDD12 Гадание на книгах", "\uD83D\uDCDA", "%s/%s".formatted(DIVINATION, DivinationType.BOOKS))),
                rowOf(button("\uD83D\uDD12 Гадание на небесных телах", "\uD83C\uDF0C", "%s/%s".formatted(DIVINATION, DivinationType.CELESTIAL))),
                rowOf(button("⬅ Назад", "menu")));
    }

    private Response createNotImplementedCallbackQueryAnswer(String queryId) {
        return new Response(BotApiMethodFactory.callbackQueryAnswer(queryId, "Эта функция еще в разработке", false));
    }
}