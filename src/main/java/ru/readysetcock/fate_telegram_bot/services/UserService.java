package ru.readysetcock.fate_telegram_bot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.readysetcock.fate_telegram_bot.model.domain.User;
import ru.readysetcock.fate_telegram_bot.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void userUpdateService(Update update) {
        if (update.getMessage() != null) {
            Message message = update.getMessage();
            org.telegram.telegrambots.meta.api.objects.User user = message.getFrom();
            repository.save(new User(user.getId(), message.getChatId(), user.getFirstName(), user.getLastName(), user.getUserName()));
        }
    }
}