package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "runes_entities")
@NoArgsConstructor
@Getter
public class RuneEntity extends BaseCatalogueDomain {

    @Column(name = "runes_string", nullable = false)
    private String symbol;
    private String meaning;
    private String description;



    public RuneEntity(String rusName, String engName, String imagePath, String symbol, String meaning, String descripton){
        super(rusName, engName, imagePath);
        this.symbol = symbol;
        this.meaning = meaning;
        this.description = descripton;
    }
}
