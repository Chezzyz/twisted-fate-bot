package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@MappedSuperclass
@NoArgsConstructor
@Immutable
@Getter
public abstract class BaseCatalogueDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter
    private Integer id;

    @Column(name = "rus_name", nullable = false)
    private String rusName;

    @Column(name = "eng_name", nullable = false)
    private String engName;

    @Column(name = "image_file_id")
    private String imageFileId;

    protected BaseCatalogueDomain(String rusName, String engName, String imageFileId) {
        this.rusName = rusName;
        this.engName = engName;
        this.imageFileId = imageFileId;
    }
}
