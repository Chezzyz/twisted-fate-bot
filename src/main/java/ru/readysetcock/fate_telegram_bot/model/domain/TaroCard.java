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

    public TaroCard(String rusName, String engName, String imageFileId, Integer cardNumber, String description, String features) {
        super(rusName, engName, imageFileId);
        this.cardNumber = cardNumber;
        this.description = description;
        this.features = features;
    }
}
