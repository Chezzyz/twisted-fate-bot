package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "kabbalistic_numbers")
@Getter
@NoArgsConstructor
public class KabbalisticNumber {

    @Id
    @Column(name = "num_value")
    private Integer numValue;

    @Column(name = "letters")
    private String letters;

    @Column(name = "meaning", nullable = false)
    private String meaning;

    @Column(name = "divination_meaning")
    private String divinationMeaning;
}