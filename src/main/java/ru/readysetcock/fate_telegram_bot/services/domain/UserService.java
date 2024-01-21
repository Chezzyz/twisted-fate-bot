package ru.readysetcock.fate_telegram_bot.services.domain;

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

    public void updateUser(Update update) {
        Message message = update.hasCallbackQuery() ? update.getCallbackQuery().getMessage() : update.getMessage();
        Chat chat = message.getChat();
        if (!repository.existsByTgUserIdAndFirstNameAndLastNameAndUserName(chat.getId(), chat.getFirstName(), chat.getLastName(), chat.getUserName())) {
            repository.save(new User(chat.getId(), chat.getFirstName(), chat.getLastName(), chat.getUserName()));
        }
    }

    public User findByUserId(Long userId) {
        return repository.findByTgUserId(userId);
    }

    public boolean hasState(Long userId) {
        return repository.existsByTgUserIdAndStateIsNotNull(userId);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public Iterable<User> findAll() {
        return repository.findAll();
    }
}