package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.readysetcock.fate_telegram_bot.services.functions.divination.DivinationTopic;

import java.util.Map;
import java.util.function.Supplier;

@Entity
@Table(name = "taro_cards_meaning")
@Getter
@NoArgsConstructor
public class TaroCardMeaning {

    @Id
    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "love")
    private String love;

    @Column(name = "job")
    private String job;

    @Column(name = "health")
    private String health;

    @Column(name = "growth")
    private String growth;

    @Column(name = "spirit")
    private String spirit;

    public TaroCardMeaning(Integer cardId, String love, String job, String health, String growth, String spirit) {
        this.cardId = cardId;
        this.love = love;
        this.job = job;
        this.health = health;
        this.growth = growth;
        this.spirit = spirit;
    }

    @Transient
    private final Map<String, Supplier<String>> topicMap = Map.ofEntries(
            Map.entry(DivinationTopic.LOVE.toString(), this::getLove),
            Map.entry(DivinationTopic.JOB.toString(), this::getJob),
            Map.entry(DivinationTopic.HEALTH.toString(), this::getHealth),
            Map.entry(DivinationTopic.GROWTH.toString(), this::getGrowth),
            Map.entry(DivinationTopic.SPIRIT.toString(), this::getSpirit)
    );

    public String getMeaningByTopic(String topicName) {
        return topicMap.get(topicName).get();
    }
}