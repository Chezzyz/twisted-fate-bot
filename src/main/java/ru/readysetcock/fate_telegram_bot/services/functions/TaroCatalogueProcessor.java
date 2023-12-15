package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCard;
import ru.readysetcock.fate_telegram_bot.repository.TaroCardRepository;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommand;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;

@Service
@RequiredArgsConstructor
public class TaroCatalogueProcessor implements BotFunctionProcessor, BotCommandProcessor {

    private final TaroCardRepository repository;

    @Override
    public BotFunction getFunction() {
        return BotFunction.TAROS;
    }

    @Override
    public BotCommand getCommand() {
        return BotCommand.TAROS;
    }

    @Override
    public Response process(Message message) {
        return sendButtonsWithCards(message);
    }

    @Override
    public Response process(CallbackQuery query) {
        String data = query.getData();
        if (data.equals(BotFunction.TAROS.getFunctionName())) {
            return editToButtonsWithCards(query);
        } else if (data.startsWith(BotFunction.TAROS.getFunctionName() + "/id/")) {
            return sendTaroCard(query);
        } else if (data.startsWith(BotFunction.TAROS.getFunctionName() + "/delete")) {
            return deleteCard(query);
        }
        return new Response();
    }

    private Response editToButtonsWithCards(CallbackQuery query) {
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Выберите карту", getAllCardsKeyboard()));
    }

    private Response sendButtonsWithCards(Message message) {
        return new Response(BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(), "Выберите карту", getAllCardsKeyboard()));
    }

    private Response deleteCard(CallbackQuery query) {
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.deleteMessage(message.getChatId(), message.getMessageId()));
    }

    private Response sendTaroCard(CallbackQuery query) {
        int id = Integer.parseInt(query.getData().replace(BotFunction.TAROS.getFunctionName() + "/id/", ""));
        TaroCard taroCard = getTaroCardById(id);
        if (taroCard == null) {
            return new Response(BotApiMethodFactory.callbackQueryAnswer(query.getId()));
        }

        return deleteOnlyCardResponse(taroCard, query);
    }

    private Response deleteOnlyCardResponse(TaroCard taroCard, CallbackQuery query) {
        SendPhoto sendPhoto = BotApiMethodFactory.messageWithPhoto(query.getMessage().getChatId(), taroCard.getImageFileId(),
                createTaroDescriptionMessage(taroCard),
                InlineKeyboardBuilder.createKeyboardOf(rowOf(button("⬅ Назад", "%s/delete".formatted(BotFunction.TAROS.getFunctionName())))));

        return Response.builder()
                .photo(sendPhoto)
                .methods(Collections.singletonList(BotApiMethodFactory.callbackQueryAnswer(query.getId())))
                .build();
    }

    private TaroCard getTaroCardById(int id) {
        return repository.findById(id).orElse(null);
    }

    private InlineKeyboardMarkup getAllCardsKeyboard() {
        List<TaroCard> taroCardList = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        repository.findAll().forEach(taroCardList::add);
        for (int i = 0; i < taroCardList.size(); i++) {
            buttons.add(button(taroCardList.get(i).getRusName(), taroCardList.get(i).getSymbol(), BotFunction.TAROS.getFunctionName() + "/id/" + (i + 1)));
        }
        buttons.add(button("⬅ Назад", BotFunction.CATALOGUE.getFunctionName()));
        return InlineKeyboardBuilder.createKeyboardOf(buttons);
    }

    private String getPrettyFeaturesById(TaroCard card) {
        return Arrays.stream(
                card.getFeatures().split(", ")).map("⭐️ %s%n"::formatted).collect(Collectors.joining());
    }

    private String createTaroDescriptionMessage(TaroCard taroCard) {
        return """
                <b>Название</b>: %s (%s) %s
                        
                <b>Описание</b>: %s
                        
                <b>Характеристики</b>:
                %s""".formatted(taroCard.getRusName(), taroCard.getEngName(), taroCard.getSymbol(), taroCard.getDescription(), getPrettyFeaturesById(taroCard));
    }
}