package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@MappedSuperclass
@NoArgsConstructor
@Immutable
@Getter
public abstract class BaseCatalogueDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "rus_name", nullable = false)
    private String rusName;

    @Column(name = "eng_name", nullable = false)
    private String engName;

    @Column(name = "image_path")
    private String imagePath;


    protected BaseCatalogueDomain(String rusName, String engName, String imagePath) {
        this.rusName = rusName;
        this.engName = engName;
        this.imagePath = imagePath;
    }
}
