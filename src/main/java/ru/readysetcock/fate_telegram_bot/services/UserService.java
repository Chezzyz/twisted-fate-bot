package ru.readysetcock.fate_telegram_bot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.readysetcock.fate_telegram_bot.model.domain.User;
import ru.readysetcock.fate_telegram_bot.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void userUpdateService(Update update) {
            Message message = update.getCallbackQuery().getMessage();
            Chat chat = message.getChat();
            repository.save(new User(chat.getId(), message.getChatId(), chat.getFirstName(), chat.getLastName(), chat.getUserName()));
        }
    }