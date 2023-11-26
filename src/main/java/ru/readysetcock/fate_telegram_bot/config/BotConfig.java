package ru.readysetcock.fate_telegram_bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfig {

    @Value("${bot.api.token}")
    private String botApiToken;

    @Bean(name = "botToken")
    public String botToken(){
        return botApiToken;
    }

}
