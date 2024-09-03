package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.readysetcock.fate_telegram_bot.formatters.LayoutFormatter;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroLayout;
import ru.readysetcock.fate_telegram_bot.repository.TaroLayoutRepository;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommand;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;
@Service
@RequiredArgsConstructor
public class TaroLayoutProcessor implements BotFunctionProcessor, BotCommandProcessor {

    private final TaroLayoutRepository repository;

    @Override
    public BotFunction getFunction() { return BotFunction.TARO_LAYOUTS; }

    @Override
    public BotCommand getCommand() { return BotCommand.TARO_LAYOUTS; }

    @Override
    public Response process(Message message) {
        return sendButtonsWithLayouts(message);
    }

    @Override
    public Response process(CallbackQuery query) {
        String data = query.getData();
        if (data.equals(BotFunction.TARO_LAYOUTS.getFunctionName())) {
            return editToButtonsWithLayouts(query);
        } else if (data.startsWith(BotFunction.TARO_LAYOUTS.getFunctionName() + "/id/")) {
            return sendTaroLayout(query);
        } else if (data.startsWith(BotFunction.TARO_LAYOUTS.getFunctionName() + "/delete")) {
            return deleteTaroLayout(query);
        }
        return new Response();
    }

    private Response editToButtonsWithLayouts(CallbackQuery query) {
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Выберите расклад", getAllLayoutsKeyboard()));
    }

    private Response sendButtonsWithLayouts(Message message) {
        return new Response(BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(), "Выберите расклад", getAllLayoutsKeyboard()));
    }

    private Response deleteTaroLayout(CallbackQuery query) {
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.deleteMessage(message.getChatId(), message.getMessageId()));
    }

    private Response sendTaroLayout(CallbackQuery query) {
        int id = Integer.parseInt(query.getData().replace(BotFunction.TARO_LAYOUTS.getFunctionName() + "/id/", ""));
        TaroLayout taroLayout = getTaroLayoutById(id);
        if (taroLayout == null) {
            return new Response(BotApiMethodFactory.callbackQueryAnswer(query.getId()));
        }

        return deleteOnlyLayoutResponse(taroLayout, query);
    }

    private Response deleteOnlyLayoutResponse(TaroLayout taroLayout, CallbackQuery query) {
        SendPhoto sendPhoto = BotApiMethodFactory.messageWithPhoto(query.getMessage().getChatId(), taroLayout.getImageFileId(),
                createTaroDescriptionMessage(taroLayout),
                InlineKeyboardBuilder.createKeyboardOf(rowOf(button("⬅ Назад", "%s/delete".formatted(BotFunction.TARO_LAYOUTS.getFunctionName())))));

        return Response.builder()
                .photo(sendPhoto)
                .methods(Collections.singletonList(BotApiMethodFactory.callbackQueryAnswer(query.getId())))
                .build();
    }

    private TaroLayout getTaroLayoutById(int id) { return repository.findById(id).orElse(null); }

    private InlineKeyboardMarkup getAllLayoutsKeyboard() {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        repository.findAll().forEach(taroLayout -> buttons.add(button(taroLayout.getRusName(), taroLayout.getSymbol(),
                BotFunction.TARO_LAYOUTS.getFunctionName() + "/id/" + taroLayout.getId())));
        buttons.add(button("⬅ Назад", BotFunction.CATALOGUE.getFunctionName()));
        return InlineKeyboardBuilder.createKeyboardOf(buttons);
    }

    private String createTaroDescriptionMessage(TaroLayout taroLayout) {
        return """
                <b>Название</b>: %s (%s) %s

                <b>Положения</b>:
                %s
                <b>Описание</b>: %s""".formatted(taroLayout.getRusName(), taroLayout.getEngName(), taroLayout.getSymbol(),
                LayoutFormatter.formatLayoutPositions(taroLayout.getSchemeInfo().split("&")), taroLayout.getDescription());
    }
}
