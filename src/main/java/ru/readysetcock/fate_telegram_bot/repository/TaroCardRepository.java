package ru.readysetcock.fate_telegram_bot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCard;

import java.util.List;

@Repository
public interface TaroCardRepository extends CrudRepository<TaroCard, Integer> {
    @Query("SELECT t FROM TaroCard t WHERE t.isMajor = true")
    List<TaroCard> findTaroCardsByMajorIsTrue();

    TaroCard findTaroCardById(int id);
}