package ru.readysetcock.fate_telegram_bot.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readysetcock.fate_telegram_bot.model.domain.ZodiacSign;

import java.util.Optional;

@Repository
    public interface ZodiacSignRepository extends CrudRepository<ZodiacSign, Integer> {


}
