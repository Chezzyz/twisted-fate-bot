package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "runes")
@NoArgsConstructor
@Getter
public class RuneEntity extends BaseCatalogueDomain {

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "translation", nullable = false)
    private String translation;

    @Column(name = "description", nullable = false)
    private String description;



    public RuneEntity(String rusName, String engName, String imagePath, String symbol, String translation, String description){
        super(rusName, engName, imagePath);
        this.symbol = symbol;
        this.translation = translation;
        this.description = description;
    }
}
