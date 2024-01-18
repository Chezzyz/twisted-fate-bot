package ru.readysetcock.fate_telegram_bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readysetcock.fate_telegram_bot.model.domain.KabbalisticNumber;

@Repository
public interface KabbalisticNumberRepository extends CrudRepository<KabbalisticNumber,Integer> {
    KabbalisticNumber findByLettersContaining(char symbol);
    Boolean existsByNumValue(int value);
}
