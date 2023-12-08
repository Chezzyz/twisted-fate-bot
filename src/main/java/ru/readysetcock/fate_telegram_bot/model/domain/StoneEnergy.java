package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stone_energy")
@NoArgsConstructor
@Getter
public class StoneEnergy extends BaseCatalogueDomain {

    @Column(name = "esoteric_description", nullable = false)
    private String esotericDescription;

    @Column(name = "real_description", nullable = false)
    private String realDescription;

    public StoneEnergy(String rusName, String engName, String imagePath, String esotericDescription, String realDescription) {
        super(rusName, engName, imagePath);
        this.esotericDescription = esotericDescription;
        this.realDescription = realDescription;
    }
}
