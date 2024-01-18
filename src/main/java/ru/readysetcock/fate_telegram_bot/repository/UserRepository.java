package ru.readysetcock.fate_telegram_bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readysetcock.fate_telegram_bot.model.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByTgUserId(Long tgUserId);

    boolean existsByTgUserIdAndStateIsNotNull(long tgUserId);

    boolean existsByTgUserIdAndFirstNameAndLastNameAndUserName(Long tgUserId, String firstName, String lastName, String userName);
}