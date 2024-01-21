package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCard;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCardOfTheDay;
import ru.readysetcock.fate_telegram_bot.model.domain.User;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommand;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;
import ru.readysetcock.fate_telegram_bot.services.domain.TaroCardMeaningService;
import ru.readysetcock.fate_telegram_bot.services.domain.TaroCardOfTheDayService;
import ru.readysetcock.fate_telegram_bot.services.domain.TaroCardService;
import ru.readysetcock.fate_telegram_bot.services.domain.UserService;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.random.RandomGenerator;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;

@Service
@RequiredArgsConstructor
public class CardOfTheDayProcessor implements BotFunctionProcessor, BotCommandProcessor {
    private static final int AMOUNT_OF_TARO_CARDS = 78;
    private final RandomGenerator random = RandomGenerator.getDefault();
    private final TaroCardOfTheDayService cardOfTheDayService;
    private final TaroCardService taroCardService;
    private final TaroCardMeaningService meaningService;
    private final UserService userService;

    @Override
    public BotCommand getCommand() {
        return BotCommand.CARD_OF_THE_DAY;
    }

    @Override
    public BotFunction getFunction() {
        return BotFunction.CARD_OF_THE_DAY;
    }

    @Override
    public Response process(Message message) {
        return sendCardOfTheDay(message);
    }

    @Override
    public Response process(CallbackQuery query) {
        Message message = query.getMessage();
        String data = query.getData();
        if (data.equals(BotFunction.CARD_OF_THE_DAY.toString())) {
            return sendCardOfTheDay(message);
        } else if (data.equals("%s/delete".formatted(BotFunction.CARD_OF_THE_DAY))) {
            return deleteMessage(message);
        }
        return new Response();
    }

    public SendPhoto getTaroCardOfTheDayMessage(User user, TaroCard card) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC+5"));
        return BotApiMethodFactory.messageWithPhoto(user.getTgUserId(), card.getImageFileId(), """
                <b>%s %s %s</b>
                 
                <b>Ваша карта дня:
                 
                %s (%s) %s
                                    
                Описание</b>: %s
                """.formatted(zonedDateTime.getDayOfMonth(), zonedDateTime.getMonth().getDisplayName(TextStyle.FULL, new Locale("ru")),
                zonedDateTime.getYear(), card.getRusName(), card.getEngName(), card.getSymbol(),
                meaningService.findById(card.getId()).getCardOfTheDay()), InlineKeyboardBuilder.createKeyboardOf(rowOf(button("🗑️ Удалить", "%s/delete".formatted(BotFunction.CARD_OF_THE_DAY)))));
    }

    public TaroCard getTaroCardOfTheDay(RandomGenerator random) {
        return taroCardService.findById(random.nextInt(1, AMOUNT_OF_TARO_CARDS + 1));
    }

    private Response deleteMessage(Message message) {
        return new Response(BotApiMethodFactory.deleteMessage(message.getChatId(), message.getMessageId()));
    }

    private Response sendCardOfTheDay(Message message) {
        TaroCard card;
        Long userId = message.getChatId();
        if (!cardOfTheDayService.existById(userId)) {
            card = getTaroCardOfTheDay(random);
            cardOfTheDayService.save(new TaroCardOfTheDay(userId,
                    card.getId(), Instant.now()));
        } else {
            card = taroCardService.findById(cardOfTheDayService.findById(userId).getCardId());
        }
        return Response.builder()
                .photo(getTaroCardOfTheDayMessage(userService.findByUserId(userId), card))
                .build();
    }
}