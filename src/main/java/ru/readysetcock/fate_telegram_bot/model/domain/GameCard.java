package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game_cards")
@NoArgsConstructor
@Getter
public class GameCard extends BaseCatalogueDomain {
    @Column(name = "card_value", nullable = false)
    private String cardValue;

    public GameCard(String rusName, String engName, String imageFileId, String cardValue) {
        super(rusName, engName, imageFileId);
        this.cardValue = cardValue;
    }
}
