package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "taro_cards")
@Getter
@NoArgsConstructor
public class TaroCard extends BaseCatalogueDomain {
    @Column(name = "card_number", nullable = false)
    private Integer cardNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "features")
    private String features;

    @Column(name = "symbol")
    private String symbol;

    public TaroCard(String rusName, String engName, String imagePath, Integer cardNumber, String description, String features, String symbol) {
        super(rusName, engName, imagePath);
        this.cardNumber = cardNumber;
        this.description = description;
        this.features = features;
        this.symbol = symbol;
    }
}
