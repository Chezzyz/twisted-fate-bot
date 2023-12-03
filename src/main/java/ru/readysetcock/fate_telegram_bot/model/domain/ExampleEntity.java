package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "example_entities")
@NoArgsConstructor
@Getter
public class ExampleEntity extends BaseCatalogueDomain {

    @Column(name = "example_string", nullable = false)
    private String exampleString;

    public ExampleEntity(String rusName, String engName, String imagePath, String exampleString) {
        super(rusName, engName, imagePath);
        this.exampleString = exampleString;
    }
}
