package ru.readysetcock.fate_telegram_bot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCardMeaning;

import java.util.List;

public interface TaroCardMeaningRepository extends CrudRepository<TaroCardMeaning, Integer> {
}