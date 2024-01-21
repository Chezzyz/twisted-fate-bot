package ru.readysetcock.fate_telegram_bot.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EntityScan(basePackages = {"ru.readysetcock.fate_telegram_bot.model.domain"})
@EnableJpaRepositories(basePackages = {"ru.readysetcock.fate_telegram_bot.repository"})
@EnableScheduling
public class JpaConfig {
}
