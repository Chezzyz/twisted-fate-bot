package ru.readysetcock.fate_telegram_bot.messages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс для создания клавиатур, встроенных в сообщения.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InlineKeyboardBuilder {

    /**
     * Создает разметку клавиатуры с перечисленными строками.
     *
     * @param rows список строк с кнопками
     * @return разметка клавиатуры
     */
    @SafeVarargs
    public static InlineKeyboardMarkup createKeyboardOf(List<InlineKeyboardButton>... rows) {
        return new InlineKeyboardMarkup(List.of(rows));
    }

    /**
     * Создает разметку клавиатуры с перечисленными кнопками.
     * При этом кнопки будут строится по очереди в 2 столбца.
     * Если кнопок нечетное количество, последняя кнопка будет занимать 2 столца.
     *
     * @param buttons список кнопок
     * @return разметка клавиатуры
     */
    public static InlineKeyboardMarkup createKeyboardOf(List<InlineKeyboardButton> buttons) {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (int i = 0; i < buttons.size(); i += 2) {
            if (i + 1 == buttons.size()) {
                rows.add(Collections.singletonList(buttons.get(i)));
                break;
            }
            rows.add(List.of(buttons.get(i), buttons.get(i + 1)));
        }
        return new InlineKeyboardMarkup(rows);
    }

    /**
     * Создает строку кнопок
     *
     * @param buttons кнопки, которые будут помещены в строку
     * @return список кнопок
     */
    public static List<InlineKeyboardButton> rowOf(InlineKeyboardButton... buttons) {
        return List.of(buttons);
    }

    /**
     * Создает кнопку для клавиатуры без эмоджи в конце
     *
     * @param text текст кнопки
     * @param data текст, который придет в ответе по нажатию на кнопку
     * @return кнопка
     */
    public static InlineKeyboardButton button(@NonNull String text, @NonNull String data) {
        return button(text, null, data);
    }

    /**
     * Создает кнопку для кливиатуры с эмоджи в конце
     *
     * @param text  текст кнопки
     * @param emoji эмоджи
     * @param data  текст, который придет в ответе по нажатию на кнопку
     * @return кнопка
     */
    public static InlineKeyboardButton button(@NonNull String text, String emoji, @NonNull String data) {
        return InlineKeyboardButton.builder()
                .text(emoji == null ? text : "%s %s".formatted(text, emoji))
                .callbackData(data)
                .build();
    }

}
