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
import ru.readysetcock.fate_telegram_bot.repository.TaroCardMeaningRepository;
import ru.readysetcock.fate_telegram_bot.repository.TaroCardOfTheDayRepository;
import ru.readysetcock.fate_telegram_bot.repository.TaroCardRepository;
import ru.readysetcock.fate_telegram_bot.repository.UserRepository;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommand;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;

import java.time.LocalDateTime;
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
    private final TaroCardOfTheDayRepository cardOfTheDayRepository;
    private final TaroCardRepository taroCardRepository;
    private final TaroCardMeaningRepository taroCardMeaningRepository;
    private final UserRepository userRepository;

    @Override
    public BotCommand getCommand() {
        return BotCommand.CARDOFTHEDAY;
    }

    @Override
    public BotFunction getFunction() {
        return BotFunction.CARDOFTHEDAY;
    }

    @Override
    public Response process(Message message) {
        return sendCardOfTheDay(message);
    }

    @Override
    public Response process(CallbackQuery query) {
        Message message = query.getMessage();
        String data = query.getData();
        if (data.equals(BotFunction.CARDOFTHEDAY.toString())) {
            return sendCardOfTheDay(message);
        } else if (data.equals("%s/delete".formatted(BotFunction.CARDOFTHEDAY))) {
            return deleteMessage(message);
        }
        return new Response();
    }

    private Response sendCardOfTheDay(Message message) {
        TaroCard card;
        Long userId = message.getChatId();
        if (cardOfTheDayRepository.existsByUserId(userId).equals(Boolean.FALSE)) {
            card = getTaroCardOfTheDay(random);
            cardOfTheDayRepository.save(new TaroCardOfTheDay(userId,
                    card.getId(), LocalDateTime.now()));
        } else {
            card = taroCardRepository.findTaroCardById(cardOfTheDayRepository.findTaroCardOfTheDayByUserId(userId).getCardId());
        }
        return Response.builder()
                .photo(getTaroCardOfTheDayMessage(userRepository.findByTgUserId(userId), card))
                .build();
    }

    private Response deleteMessage(Message message) {
        return new Response(BotApiMethodFactory.deleteMessage(message.getChatId(), message.getMessageId()));
    }

    public SendPhoto getTaroCardOfTheDayMessage(User user, TaroCard card) {
        return BotApiMethodFactory.messageWithPhoto(user.getChatId(), card.getImageFileId(), """
                 <b>%s %s %s</b>
                 
                <b>Ваша карта дня:
                 
                %s (%s) %s
                                    
                Описание</b>: %s
                """.formatted(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("ru")),
                LocalDateTime.now().getYear(), card.getRusName(), card.getEngName(), card.getSymbol(),
                taroCardMeaningRepository.findTaroCardMeaningByCardId(card.getId()).getCardOfTheDay()), InlineKeyboardBuilder.createKeyboardOf(rowOf(button("⬅ Удалить", "%s/delete".formatted(BotFunction.CARDOFTHEDAY)))));
    }

    public TaroCard getTaroCardOfTheDay(RandomGenerator random) {
        return taroCardRepository.findTaroCardById(random.nextInt(1, AMOUNT_OF_TARO_CARDS + 1));
    }
}