package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCard;
import ru.readysetcock.fate_telegram_bot.repository.TaroCardRepository;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;

@Service
@AllArgsConstructor
public class TaroCatalogueProcessor implements BotFunctionProcessor {

    private final TaroCardRepository repository;

    @Override
    public BotFunction getFunction() {
        return BotFunction.TAROS;
    }

    private Response sendButtonsWithCards(CallbackQuery query) {
        List<TaroCard> taroCardList = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        repository.findAll().forEach(taroCardList::add);
        for (int i = 0; i < taroCardList.size(); i++) {
            buttons.add(button(taroCardList.get(i).getRusName(), taroCardList.get(i).getSymbol(), BotFunction.TAROS.getFunctionName() + "/" + i));
        }
        buttons.add(button("⬅ Назад", BotFunction.CATALOGUE.getFunctionName()));
        InlineKeyboardMarkup keyboard = InlineKeyboardBuilder.createKeyboardOf(buttons);
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Выберите карту", keyboard));
    }

    private Response sendTaroCard(CallbackQuery query) {
        int id = Integer.parseInt(query.getData().replace(BotFunction.TAROS.getFunctionName() + "/", "")) + 1;
        TaroCard taroCard = getTaroCardById(id);
        if (taroCard == null) {
            return new Response(BotApiMethodFactory.callbackQueryAnswer(query.getId()));
        }
        Message message = query.getMessage();
        SendPhoto sendPhoto = BotApiMethodFactory.messageWithPhoto(message.getChatId(), taroCard.getImageFileId(),
                String.format("Карта: %s (%s) %s%n%nОписание: %s%n%nХарактеристики:%n%s",
                        taroCard.getRusName(),
                        taroCard.getEngName(),
                        taroCard.getSymbol(),
                        taroCard.getDescription(),
                        getPrettyFeaturesById(taroCard)));
        return Response.builder()
                .photo(sendPhoto)
                .methods(Arrays.asList(
                        DeleteMessage.builder()
                                .chatId(message.getChatId())
                                .messageId(message.getMessageId())
                                .build(),
                        BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(), "\uD83D\uDC47\uD83C\uDFFB", InlineKeyboardBuilder.createKeyboardOf(
                                rowOf(button("⬅ Назад", BotFunction.TAROS.getFunctionName()))))))
                .build();
    }

    private TaroCard getTaroCardById(int id) {
        return repository.findById(id).orElse(null);
    }

    private String getPrettyFeaturesById(TaroCard card) {
        return Arrays.stream(
                card.getFeatures().split(", ")).map("❇️ %s%n"::formatted).collect(Collectors.joining());
    }

    @Override
    public Response process(CallbackQuery query) {
        if (query.getData().equals(BotFunction.TAROS.getFunctionName())) {
            return sendButtonsWithCards(query);
        } else if (query.getData().contains(BotFunction.TAROS.getFunctionName())) {
            return sendTaroCard(query);
        }
        return null;
    }
}