package ru.readysetcock.fate_telegram_bot.messages;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Builder
public record Response(List<BotApiMethod<? extends Serializable>> methods, SendPhoto photo, SendMediaGroup photos) {
    @SafeVarargs
    public Response(BotApiMethod<? extends Serializable>... method) {
        this(List.of(method), null, null);
    }

    public Response() {
        this(null, null, null);
    }
}