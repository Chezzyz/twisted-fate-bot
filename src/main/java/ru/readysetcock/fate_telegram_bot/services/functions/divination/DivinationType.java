package ru.readysetcock.fate_telegram_bot.services.functions.divination;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DivinationType {
    TARO("taro"),
    GAMECARDS("game"),
    RUNES("runes"),
    BOOKS("books"),
    CELESTIAL("celestial");

    @Getter
    private final String functionName;

    @Override
    public String toString() {
        return functionName;
    }
}