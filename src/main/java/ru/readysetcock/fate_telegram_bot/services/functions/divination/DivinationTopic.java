package ru.readysetcock.fate_telegram_bot.services.functions.divination;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Список тематик вопросов для гадания
 */
@AllArgsConstructor
public enum DivinationTopic {
    LOVE("love", "Любовь и отношения", "❤️"),
    JOB("job", "Карьера и работа", "💼"),
    SPIRIT("spirit", "Духовный рост", "🧘"),
    HEALTH("health", "Здоровье", "💊"),
    DECISION("decision", "Принятие решений", "🤔"),
    GROWTH("growth", "Личностное развитие", "📈"),
    INSIGNIFICANT("ins", "Не имеет значения", "⬜️"),
    DONTWANNATELL("dwt", "Не хочу указывать", "🙊");

    private static final Map<String, DivinationTopic> FUNCTION_NAME_MAP = new HashMap<>();

    static {
        for (DivinationTopic topic : values()) {
            FUNCTION_NAME_MAP.put(topic.functionName, topic);
        }
    }

    @Getter
    private final String functionName;
    @Getter
    private final String rusName;
    @Getter
    private final String emoji;

    public static String getRusNameByFunctionName(String functionName) {
        DivinationTopic topic = FUNCTION_NAME_MAP.get(functionName);
        return (topic != null) ? topic.emoji + topic.rusName + topic.emoji : null;
    }

    @Override
    public String toString() {
        return functionName;
    }
}