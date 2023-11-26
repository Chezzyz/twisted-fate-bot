package ru.readysetcock.fate_telegram_bot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"ru.readysetcock.fate_telegram_bot.repository"})
public class JpaConfig {
}
