package ru.readysetcock.fate_telegram_bot.services.schedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import ru.readysetcock.fate_telegram_bot.controllers.TelegramController;
import ru.readysetcock.fate_telegram_bot.messages.MessageSender;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCard;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCardOfTheDay;
import ru.readysetcock.fate_telegram_bot.model.domain.User;
import ru.readysetcock.fate_telegram_bot.services.domain.TaroCardOfTheDayService;
import ru.readysetcock.fate_telegram_bot.services.domain.UserService;
import ru.readysetcock.fate_telegram_bot.services.functions.CardOfTheDayProcessor;

import java.time.Instant;
import java.util.random.RandomGenerator;

@Service
@RequiredArgsConstructor
public class CardOfTheDayScheduler {
    private final CardOfTheDayProcessor processor;
    private final TelegramController controller;
    private final UserService userService;
    private final TaroCardOfTheDayService cardOfTheDayService;

    @Scheduled(cron = "0 0 10 * * ?", zone = "GMT+05") // 0 - секунды, 0 - минуты, 10-часы, * - каждый день, * - каждый месяц, ? - каждый код
    public void sendCardOfTheDayMessage() {
        RandomGenerator random = RandomGenerator.getDefault();
        userService.findAll().forEach(user -> sendCard(user, random));
    }

    private void sendCard(User user, RandomGenerator random) {
        TaroCard card = processor.getTaroCardOfTheDay(random);
        cardOfTheDayService.save(new TaroCardOfTheDay(user.getTgUserId(), card.getId(), Instant.now()));
        SendPhoto sendPhoto = processor.getTaroCardOfTheDayMessage(user, card);
        MessageSender.sendResponse(Response.builder()
                .photo(sendPhoto)
                .build(), controller);
    }
}