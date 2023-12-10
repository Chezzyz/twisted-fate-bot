package ru.readysetcock.fate_telegram_bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCardEntity;

@Repository
public interface TaroCardRepository extends CrudRepository<TaroCardEntity, Integer> {
}
