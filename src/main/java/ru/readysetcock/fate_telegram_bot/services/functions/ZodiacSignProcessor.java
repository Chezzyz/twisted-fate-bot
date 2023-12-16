package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.readysetcock.fate_telegram_bot.formatters.FeaturesFormatter;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.ZodiacSign;
import ru.readysetcock.fate_telegram_bot.repository.ZodiacSignRepository;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommand;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;

@Service
@RequiredArgsConstructor
public class ZodiacSignProcessor implements BotFunctionProcessor, BotCommandProcessor {

    private final ZodiacSignRepository zodiacSignRepository;

    @Override
    public BotFunction getFunction() {
        return BotFunction.ZODIAC;
    }

    @Override
    public BotCommand getCommand() {
        return BotCommand.ZODIACS;
    }

    @Override
    public Response process(Message message) {
        return sendButtonsWithZodiacSigns(message);
    }

    @Override
    public Response process(CallbackQuery query) {
        String data = query.getData();
        if (data.equals(BotFunction.ZODIAC.getFunctionName())) {
            return editToButtonsWithZodiacSigns(query);
        } else if (data.contains(BotFunction.ZODIAC.getFunctionName() + "/id/")) {
            return sendZodiacInfo(query);
        } else if (data.contains(BotFunction.ZODIAC.getFunctionName() + "/delete")) {
            return deleteSign(query);
        }

        return new Response();
    }

    private Response editToButtonsWithZodiacSigns(CallbackQuery query) {
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Выберите знак зодиака", getAllZodiacSignsKeyboard()));
    }

    private Response sendButtonsWithZodiacSigns(Message message) {
        return new Response(BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(), "Выберите знак зодиака", getAllZodiacSignsKeyboard()));
    }

    private Response sendZodiacInfo(CallbackQuery query) {
        int id = Integer.parseInt(query.getData().replace(BotFunction.ZODIAC.getFunctionName() + "/id/", ""));
        ZodiacSign zodiacSign = getZodiacSignById(id);
        if (zodiacSign == null) {
            return new Response(BotApiMethodFactory.callbackQueryAnswer(query.getId()));
        }
        return deleteOnlySignResponse(zodiacSign, query);
    }

    private Response deleteSign(CallbackQuery query) {
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.deleteMessage(message.getChatId(), message.getMessageId()));
    }

    private Response deleteOnlySignResponse(ZodiacSign zodiacSign, CallbackQuery query) {
        SendPhoto sendPhoto = BotApiMethodFactory.messageWithPhoto(query.getMessage().getChatId(), zodiacSign.getImageFileId(),
                createZodiacInfoString(zodiacSign),
                InlineKeyboardBuilder.createKeyboardOf(rowOf(button("⬅ Назад", "%s/delete".formatted(BotFunction.ZODIAC.getFunctionName())))));

        return Response.builder()
                .photo(sendPhoto)
                .methods(Collections.singletonList(BotApiMethodFactory.callbackQueryAnswer(query.getId())))
                .build();
    }

    private InlineKeyboardMarkup getAllZodiacSignsKeyboard() {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        zodiacSignRepository.findAll().forEach(sign -> buttons.add(button(sign.getRusName(), sign.getSymbol(),
                BotFunction.ZODIAC.getFunctionName() + "/id/" + sign.getId())));
        buttons.add(button("⬅ Назад", BotFunction.CATALOGUE.getFunctionName()));
        return InlineKeyboardBuilder.createKeyboardOf(buttons);
    }

    private ZodiacSign getZodiacSignById(int id) {
        return zodiacSignRepository.findById(id).orElse(null);
    }

    private String createZodiacInfoString(ZodiacSign zodiacSign) {
        return """
                <b>Название</b>: %s (%s) %s

                <b>Характеристики</b>:
                %s
                <b>Описание</b>: %s""".formatted(
                zodiacSign.getRusName(),
                zodiacSign.getEngName(),
                zodiacSign.getSymbol(),
                FeaturesFormatter.formatPrettyFeatures(zodiacSign.getFeatures().split(", ")),
                zodiacSign.getDescription()
        );
    }
}


