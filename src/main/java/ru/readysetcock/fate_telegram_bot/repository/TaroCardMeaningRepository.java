package ru.readysetcock.fate_telegram_bot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCardMeaning;


public interface TaroCardMeaningRepository extends CrudRepository<TaroCardMeaning, Integer> {
    TaroCardMeaning findTaroCardMeaningByCardId(int id);
}