package ru.readysetcock.fate_telegram_bot.services.functions.divination;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.readysetcock.fate_telegram_bot.formatters.LayoutFormatter;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.PhotoGroupBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.*;
import ru.readysetcock.fate_telegram_bot.repository.*;
import ru.readysetcock.fate_telegram_bot.services.domain.TaroCardMeaningService;
import ru.readysetcock.fate_telegram_bot.services.domain.TaroCardService;
import ru.readysetcock.fate_telegram_bot.services.domain.UserService;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunction;
import ru.readysetcock.fate_telegram_bot.services.functions.BotState;
import ru.readysetcock.fate_telegram_bot.services.functions.BotStateProcessor;

import java.util.*;
import java.util.random.RandomGenerator;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;

@Service
@RequiredArgsConstructor
public class TaroDivinationSubprocessor implements DivinationSubprocessor, BotStateProcessor {
    private final TaroLayoutRepository taroLayoutRepository;
    private final TaroCardService taroCardService;
    private final TaroCardMeaningService meaningService;
    private final UserService userService;
    private static final int TOPIC_ORDER_NUM = 3;
    private static final int MIN_OR_NOMIN_ORDER_NUM = 4;
    private static final int DECK_ORDER_NUM = 2;
    private static final int LAYOUT_ORDER_NUM = 6;
    private static final String SIMPLE_LAYOUT_ID = "3";

    @Override
    public DivinationType getDivinationType() {
        return DivinationType.TARO;
    }

    @Override
    public Set<BotState> getStates() {
        return Set.of(BotState.TARO_QUESTION);
    }

    @Override
    public Response process(CallbackQuery query) {
        String data = query.getData();
        if (data.contains("/id/")) {
            return cardReading(query);
        } else if (data.contains("min")) {
            return getLayouts(query);
        } else if (data.contains("/dwt") || data.contains("/spirit")) {
            query.setData(data.concat("/nomin"));
            return getLayouts(query);
        } else if (data.contains("/t/")) {
            return getMajorOrMinorKeyboard(query);
        } else if (data.equals("%s/%s".formatted(BotFunction.DIVINATION, DivinationType.TARO))) {
            return getTopics(query);
        }
        return new Response();
    }

    @Override
    public Response processState(Update update, String data) {
        String question = update.getMessage().getText();
        User user = userService.findByUserId(update.getMessage().getChatId());
        user.setState(null);
        userService.save(user);
        return new Response(BotApiMethodFactory.inlineKeyboardMessage(update.getMessage().getChatId(), "Ваш вопрос: %s".formatted(question),
                InlineKeyboardBuilder.createKeyboardOf(InlineKeyboardBuilder
                        .rowOf(InlineKeyboardBuilder.button("Да", "\uD83D\uDC4D", BotFunction.KABBALAH.getFunctionName().concat("/question")),
                                InlineKeyboardBuilder.button("Нет", "\uD83D\uDC4E", BotFunction.KABBALAH.getFunctionName().concat("/div"))))));
    }

    private Response getQuestion(CallbackQuery query) {
        Message message = query.getMessage();
        User user = userService.findByUserId(query.getMessage().getChatId());
        user.setState(BotState.TARO_QUESTION.getContextPattern().formatted(query.getData()));
        userService.save(user);
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), BotState.TARO_QUESTION.getText(), InlineKeyboardBuilder.createKeyboardOf(
                rowOf(button("⬅ Назад", query.getData().split("/id")[0])))));
    }

    private Response cardReading(CallbackQuery query) {
        String data = query.getData();
        Message message = query.getMessage();
        TaroLayout taroLayout = getTaroLayoutById(Integer.parseInt(data.split("/")[LAYOUT_ORDER_NUM]));
        if (taroLayout == null) {
            return new Response(BotApiMethodFactory.callbackQueryAnswer(query.getId()));
        }

        List<TaroCard> layoutCards = getLayoutCards(data, taroLayout);
        Response.ResponseBuilder builder = Response.builder()
                .methods(List.of(BotApiMethodFactory.deleteMessage(message.getChatId(), message.getMessageId()),
                        BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(), formatCardReadingText(taroLayout, layoutCards, data),
                                InlineKeyboardBuilder.createKeyboardOf(rowOf(button("⬅ Назад в меню", BotFunction.MENU.getFunctionName()))))));
        if (data.contains("id/".concat(SIMPLE_LAYOUT_ID))) {
            builder.photo(BotApiMethodFactory.messageWithPhoto(message.getChatId(), layoutCards.get(0).getImageFileId(), true));
        } else {
            builder.photos(BotApiMethodFactory.messageWithPhotoGroup(message.getChatId(), createCardPhotos(layoutCards)));
        }
        return builder.build();
    }

    private List<InputMediaPhoto> createCardPhotos(List<TaroCard> layoutCards) {
        List<InputMediaPhoto> cardPhotos = new ArrayList<>();
        layoutCards.stream().map(taroCard -> PhotoGroupBuilder.photo(taroCard.getImageFileId(),
                        "<b>Название: %s(%s) %s</b>".formatted(taroCard.getRusName(), taroCard.getEngName(), taroCard.getSymbol()), true))
                .forEach(cardPhotos::add);
        return cardPhotos;
    }

    private List<TaroCard> getLayoutCards(String data, TaroLayout taroLayout) {
        RandomGenerator random = RandomGenerator.getDefault();
        List<TaroCard> cards = new ArrayList<>();
        List<TaroCard> layoutCards = new ArrayList<>();
        Set<Integer> uniqueIndexes = new HashSet<>();
        if (data.contains("/nomin/")) {
            cards = taroCardService.getMajorCards();
        } else {
            taroCardService.findAll().forEach(cards::add);
        }
        while (uniqueIndexes.size() < taroLayout.getNumberOfCards()) {
            int randomIndex = random.nextInt(cards.size());
            uniqueIndexes.add(randomIndex);
        }
        uniqueIndexes.stream()
                .map(cards::get)
                .forEach(layoutCards::add);
        return layoutCards;
    }

    private String formatCardReadingText(TaroLayout taroLayout, List<TaroCard> layoutCards, String data) {
        String topicName = data.split("/")[TOPIC_ORDER_NUM];
        List<String> descriprionsList = new ArrayList<>();
        descriprionsList.add("""
                <b>Сначала откройте все картинки, а потом текст</b>
                                
                %s
                %s%s%s
                                
                """.formatted(DivinationTopic.getRusNameByFunctionName(topicName), taroLayout.getSymbol(), taroLayout.getRusName(), taroLayout.getSymbol()));
        for (int i = 0; i < taroLayout.getNumberOfCards(); i++) {
            String[] layoutPositionText = taroLayout.getSchemeInfo().split("&")[i].split("\\.");
            TaroCardMeaning taroCardMeaning = meaningService.findById(layoutCards.get(i).getId());
            String s = """
                    %s <tg-spoiler>%s
                    %s %s:
                    <b>%s</b></tg-spoiler>
                                        
                    """.formatted(LayoutFormatter.getEmojiOfNumber(layoutPositionText[0]),
                    layoutPositionText[1],
                    layoutCards.get(i).getRusName(), layoutCards.get(i).getSymbol(),
                    topicName.equals("decision") || topicName.equals("dwt") ? layoutCards.get(i).getDescription() : taroCardMeaning.getMeaningByTopic(topicName));
            descriprionsList.add(s);
        }
        return String.join("", descriprionsList);
    }

    private Response getMajorOrMinorKeyboard(CallbackQuery query) {
        String data = query.getData();
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Какие карты использовать?", InlineKeyboardBuilder.createKeyboardOf(
                rowOf(button("Только старшие арканы", "%s/nomin".formatted(data))),
                rowOf(button("Старшие и младшие арканы", "%s/min".formatted(data))),
                rowOf(button("⬅ Назад", data.replace(data.split("/")[MIN_OR_NOMIN_ORDER_NUM - 1], "").replace("/t/", ""))))));
    }

    private Response getTopics(CallbackQuery query) {
        Message message = query.getMessage();
        String data = query.getData();
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Выберите тему вопроса",
                InlineKeyboardBuilder.createKeyboardOf(
                        rowOf(button(DivinationTopic.LOVE.getRusName(), DivinationTopic.LOVE.getEmoji(), "%s/t/%s".formatted(data, DivinationTopic.LOVE))),
                        rowOf(button(DivinationTopic.JOB.getRusName(), DivinationTopic.JOB.getEmoji(), "%s/t/%s".formatted(data, DivinationTopic.JOB))),
                        rowOf(button(DivinationTopic.SPIRIT.getRusName(), DivinationTopic.SPIRIT.getEmoji(), "%s/t/%s".formatted(data, DivinationTopic.SPIRIT))),
                        rowOf(button(DivinationTopic.HEALTH.getRusName(), DivinationTopic.HEALTH.getEmoji(), "%s/t/%s".formatted(data, DivinationTopic.HEALTH))),
                        rowOf(button(DivinationTopic.DECISION.getRusName(), DivinationTopic.DECISION.getEmoji(), "%s/t/%s".formatted(data, DivinationTopic.DECISION))),
                        rowOf(button(DivinationTopic.GROWTH.getRusName(), DivinationTopic.GROWTH.getEmoji(), "%s/t/%s".formatted(data, DivinationTopic.GROWTH))),
                        rowOf(button(DivinationTopic.YESNO.getRusName(), DivinationTopic.YESNO.getEmoji(), "%s/t/%s".formatted(data, DivinationTopic.YESNO))),
                        rowOf(button(DivinationTopic.INSIGNIFICANT.getRusName(), DivinationTopic.INSIGNIFICANT.getEmoji(), "%s/t/%s".formatted(data, DivinationTopic.INSIGNIFICANT))),
                        rowOf(button(DivinationTopic.DONTWANNATELL.getRusName(), DivinationTopic.DONTWANNATELL.getEmoji(), "%s/t/%s".formatted(data, DivinationTopic.DONTWANNATELL))),
                        rowOf(button("⬅ Назад", data.replace("/%s".formatted(data.split("/")[DECK_ORDER_NUM - 1]), ""))))));
    }

    private Response getLayouts(CallbackQuery query) {
        Message message = query.getMessage();
        String data = query.getData();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        String topic = data.split("/")[TOPIC_ORDER_NUM];
        if (data.contains(DivinationTopic.DONTWANNATELL.getFunctionName()) || data.contains(DivinationTopic.YESNO.getFunctionName())) {
            TaroLayout simpleLayout = taroLayoutRepository.findById(3).orElseThrow();
            buttons.add(button(simpleLayout.getRusName(), simpleLayout.getSymbol(), "%s/id/%s".formatted(data, simpleLayout.getId())));
            if (data.contains(DivinationTopic.DONTWANNATELL.getFunctionName())) {
                TaroLayout linearLayout = taroLayoutRepository.findById(4).orElseThrow();
                buttons.add(button(linearLayout.getRusName(), linearLayout.getSymbol(), "%s/id/%s".formatted(data, linearLayout.getId())));
            }
        } else {
            taroLayoutRepository.findTaroLayoutsByTopicContainsOrTopicEquals(topic, "general")
                    .forEach(taroLayout ->
                            buttons.add(button(taroLayout.getRusName(),
                                    taroLayout.getSymbol(), "%s/id/%s".formatted(data, taroLayout.getId()))));
        }
        buttons.add(button("⬅ Назад", "%s/%s".formatted(BotFunction.DIVINATION, DivinationType.TARO)));
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(),
                """
                        \uD83E\uDD14<b>Перед тем как выбрать расклад, задайте вопрос у себя в голове</b>\uD83E\uDD14
                                                
                        Выберите расклад (для подробной информации о раскладах нажмите /taro_layouts )""",
                InlineKeyboardBuilder.createKeyboardOf(buttons)));
    }

    private TaroLayout getTaroLayoutById(int id) {
        return taroLayoutRepository.findById(id).orElse(null);
    }
}