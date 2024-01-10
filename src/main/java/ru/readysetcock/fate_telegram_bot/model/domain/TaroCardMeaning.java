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

    @Column(name = "yes_no")
    private String yesNo;

    @Column(name = "card_of_the_day")
    private String cardOfTheDay;

    public TaroCardMeaning(Integer cardId, String love, String job, String health, String growth, String spirit, String yesNo, String cardOfTheDay) {
        this.cardId = cardId;
        this.love = love;
        this.job = job;
        this.health = health;
        this.growth = growth;
        this.spirit = spirit;
        this.yesNo = yesNo;
        this.cardOfTheDay = cardOfTheDay;
    }

    @Transient
    private final Map<String, Supplier<String>> topicMap = Map.ofEntries(
            Map.entry(DivinationTopic.LOVE.toString(), this::getLove),
            Map.entry(DivinationTopic.JOB.toString(), this::getJob),
            Map.entry(DivinationTopic.HEALTH.toString(), this::getHealth),
            Map.entry(DivinationTopic.GROWTH.toString(), this::getGrowth),
            Map.entry(DivinationTopic.SPIRIT.toString(), this::getSpirit),
            Map.entry(DivinationTopic.YESNO.toString(), this::getYesNo)
    );

    public String getMeaningByTopic(String topicName) {
        return topicMap.get(topicName).get();
    }
}