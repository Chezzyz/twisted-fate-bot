package ru.readysetcock.fate_telegram_bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCardOfTheDay;

@Repository
public interface TaroCardOfTheDayRepository extends CrudRepository<TaroCardOfTheDay, Integer> {
    Boolean existsByUserId(Long id);
    TaroCardOfTheDay findTaroCardOfTheDayByUserId(Long id);
}