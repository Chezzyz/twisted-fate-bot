package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "taro_cards_of_the_day")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaroCardOfTheDay {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "updated_timestamp")
    private Instant updatedTimestamp;
}
