package ru.readysetcock.fate_telegram_bot.services.schedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.readysetcock.fate_telegram_bot.controllers.TelegramController;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCard;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCardOfTheDay;
import ru.readysetcock.fate_telegram_bot.repository.TaroCardOfTheDayRepository;
import ru.readysetcock.fate_telegram_bot.repository.UserRepository;
import ru.readysetcock.fate_telegram_bot.services.functions.CardOfTheDayProcessor;

import java.time.LocalDateTime;
import java.util.random.RandomGenerator;

@Service
@RequiredArgsConstructor
public class CardOfTheDayScheduler {
    private final CardOfTheDayProcessor processor;
    private final TelegramController controller;
    private final UserRepository userRepository;
    private final TaroCardOfTheDayRepository cardOfTheDayRepository;

    @Scheduled(cron = "0 0 10 * * ?")
    public void sendCardOfTheDayMessage() {
        RandomGenerator random = RandomGenerator.getDefault();
        userRepository.findAll().forEach(user -> {
                    TaroCard card = processor.getTaroCardOfTheDay(random);
            cardOfTheDayRepository.save(new TaroCardOfTheDay(user.getTgUserId(),
                    card.getId(), LocalDateTime.now()));
                    SendPhoto sendPhoto = processor.getTaroCardOfTheDayMessage(user, card);
                    try {
                        controller.execute(sendPhoto);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}