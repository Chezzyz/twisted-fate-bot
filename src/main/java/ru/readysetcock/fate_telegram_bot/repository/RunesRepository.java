package ru.readysetcock.fate_telegram_bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readysetcock.fate_telegram_bot.model.domain.Rune;

@Repository
public interface RunesRepository extends CrudRepository<Rune, Integer> {
}
