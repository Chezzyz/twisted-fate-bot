package ru.readysetcock.fate_telegram_bot.messages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PhotoGroupBuilder {

    public static List<InputMediaPhoto> photoGroup(InputMediaPhoto... photos){
        return List.of(photos);
    }

    public static InputMediaPhoto photo(String fileId, String caption, boolean spoiler){
        return InputMediaPhoto.builder()
                .caption(caption)
                .media(fileId)
                .parseMode(ParseMode.HTML)
                .isNewMedia(false)
                .hasSpoiler(spoiler)
                .build();
    }

    public static InputMediaPhoto photo(String fileId, String caption){
        return photo(fileId, caption, false);
    }

}