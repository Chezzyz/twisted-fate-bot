package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "taro_layouts")
@Getter
@NoArgsConstructor
public class TaroLayout extends BaseCatalogueDomain{
    @Column(name = "scheme_info")
    private String schemeInfo;

    @Column(name = "description")
    private String description;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "topic")
    private String topic;

    @Column(name = "number_of_cards")
    private int numberOfCards;

    public TaroLayout(String rusName, String engName, String imageFileId, String schemeInfo, String description, String symbol, String topic, int numberOfCards) {
        super(rusName, engName, imageFileId);
        this.schemeInfo = schemeInfo;
        this.description = description;
        this.symbol = symbol;
        this.topic = topic;
        this.numberOfCards = numberOfCards;
    }
}
