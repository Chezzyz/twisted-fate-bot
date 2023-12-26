package ru.readysetcock.fate_telegram_bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroLayout;

import java.util.List;

@Repository
public interface TaroLayoutRepository extends CrudRepository<TaroLayout, Integer> {
    List<TaroLayout> findTaroLayoutsByTopicContainsOrTopicEquals(String topicName, String generalTopicName);
}
