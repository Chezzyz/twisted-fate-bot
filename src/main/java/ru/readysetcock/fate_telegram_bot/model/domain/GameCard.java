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
public class GameCard extends BaseCatalogueDomain{

    @Column(name = "card_value")
    private String cardValue;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "description")
    private String description;

    public GameCard(String rusName, String engName, String imagePath, String cardValue, String symbol, String description) {
        super(rusName, engName, imagePath);
        this.cardValue = cardValue;
        this.symbol = symbol;
        this.description = description;
    }
}
