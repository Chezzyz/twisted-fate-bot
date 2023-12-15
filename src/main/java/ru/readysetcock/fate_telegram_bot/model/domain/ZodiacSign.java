package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.readysetcock.fate_telegram_bot.formatters.DateFormatter;

import java.time.LocalDate;

@Entity
@Table(name = "zodiac_signs")
@NoArgsConstructor
@Getter
public class ZodiacSign extends BaseCatalogueDomain {
    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "description")
    private String description;

    public ZodiacSign(String rusName, String engName, String imageFileId, LocalDate startDate, LocalDate endDate, String symbol, String description) {
        super(rusName, engName, imageFileId);
        this.startDate = DateFormatter.format(startDate, DateFormatter.MONTH_DAY_FORMATTER);
        this.endDate = DateFormatter.format(endDate, DateFormatter.MONTH_DAY_FORMATTER);
        this.symbol = symbol;
        this.description = description;
    }
}
