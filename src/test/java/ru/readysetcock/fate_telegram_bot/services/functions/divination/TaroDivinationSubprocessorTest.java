package ru.readysetcock.fate_telegram_bot.services.functions.divination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCard;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCardMeaning;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroLayout;
import ru.readysetcock.fate_telegram_bot.repository.TaroLayoutRepository;
import ru.readysetcock.fate_telegram_bot.services.domain.TaroCardMeaningService;
import ru.readysetcock.fate_telegram_bot.services.domain.TaroCardService;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunction;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaroDivinationSubprocessorTest {

    @Mock
    private TaroCardService cardService;
    @Mock
    private TaroLayoutRepository layoutRepository;
    @Mock
    private TaroCardMeaningService meaningService;

    private TaroDivinationSubprocessor sut;

    @BeforeEach
    public void init() {
        sut = new TaroDivinationSubprocessor(layoutRepository, cardService, meaningService);
    }

    @Test
    void processGetTopics() {
        CallbackQuery query = new CallbackQuery();
        query.setData("%s/%s".formatted(BotFunction.DIVINATION, DivinationType.TARO));
        query.setMessage(new Message());
        query.getMessage().setChat(new Chat(1L, "type"));
        query.getMessage().setMessageId(1);

        Response response = sut.process(query);

        assertTrue(response.methods() != null && !response.methods().isEmpty());
        assertEquals(1, response.methods().size());
        assertInstanceOf(EditMessageText.class, response.methods().get(0));
        EditMessageText editMessageText = (EditMessageText) response.methods().get(0);
        assertEquals(query.getMessage().getChatId().toString(), editMessageText.getChatId());
        assertEquals(query.getMessage().getMessageId(), editMessageText.getMessageId());
        assertTrue(!editMessageText.getText().isEmpty());
        assertNotNull(editMessageText.getReplyMarkup());
        assertInstanceOf(InlineKeyboardMarkup.class, editMessageText.getReplyMarkup());
        assertEquals(10, editMessageText.getReplyMarkup().getKeyboard().stream().mapToInt(List::size).sum());
    }

    @Test
    void processGetMajorOrMinorKeyboard() {
        CallbackQuery query = new CallbackQuery();
        query.setData("%s/%s/t/%s".formatted(BotFunction.DIVINATION, DivinationType.TARO, DivinationTopic.LOVE));
        query.setMessage(new Message());
        query.getMessage().setChat(new Chat(1L, "type"));
        query.getMessage().setMessageId(1);

        Response response = sut.process(query);

        assertTrue(response.methods() != null && !response.methods().isEmpty());
        assertEquals(1, response.methods().size());
        assertInstanceOf(EditMessageText.class, response.methods().get(0));
        EditMessageText editMessageText = (EditMessageText) response.methods().get(0);
        assertEquals(query.getMessage().getChatId().toString(), editMessageText.getChatId());
        assertEquals(query.getMessage().getMessageId(), editMessageText.getMessageId());
        assertTrue(!editMessageText.getText().isEmpty());
        assertNotNull(editMessageText.getReplyMarkup());
        assertInstanceOf(InlineKeyboardMarkup.class, editMessageText.getReplyMarkup());
        assertEquals(3, editMessageText.getReplyMarkup().getKeyboard().stream().mapToInt(List::size).sum());
    }

    @Test
    void processGetLayoutsWithLoveTopic() {
        CallbackQuery query = new CallbackQuery();
        query.setData("%s/%s/t/%s/nomin".formatted(BotFunction.DIVINATION, DivinationType.TARO, DivinationTopic.LOVE));
        query.setMessage(new Message());
        query.getMessage().setChat(new Chat(1L, "type"));
        query.getMessage().setMessageId(1);
        when(layoutRepository.findTaroLayoutsByTopicContainsOrTopicEquals(DivinationTopic.LOVE.toString(), "general")).thenReturn(List.of(createLayout(), createLayout()));

        Response response = sut.process(query);

        assertTrue(response.methods() != null && !response.methods().isEmpty());
        assertEquals(1, response.methods().size());
        assertInstanceOf(EditMessageText.class, response.methods().get(0));
        EditMessageText editMessageText = (EditMessageText) response.methods().get(0);
        assertEquals(query.getMessage().getChatId().toString(), editMessageText.getChatId());
        assertEquals(query.getMessage().getMessageId(), editMessageText.getMessageId());
        assertTrue(!editMessageText.getText().isEmpty());
        assertNotNull(editMessageText.getReplyMarkup());
        assertInstanceOf(InlineKeyboardMarkup.class, editMessageText.getReplyMarkup());
        assertEquals(3, editMessageText.getReplyMarkup().getKeyboard().stream().mapToInt(List::size).sum());
    }

    @Test
    void processGetLayoutsWithDwtTopic() {
        CallbackQuery query = new CallbackQuery();
        query.setData("%s/%s/t/%s/nomin".formatted(BotFunction.DIVINATION, DivinationType.TARO, DivinationTopic.DONTWANNATELL));
        query.setMessage(new Message());
        query.getMessage().setChat(new Chat(1L, "type"));
        query.getMessage().setMessageId(1);
        when(layoutRepository.findById(any())).thenReturn(Optional.of(createLayout()));

        Response response = sut.process(query);

        assertTrue(response.methods() != null && !response.methods().isEmpty());
        assertEquals(1, response.methods().size());
        assertInstanceOf(EditMessageText.class, response.methods().get(0));
        EditMessageText editMessageText = (EditMessageText) response.methods().get(0);
        assertEquals(query.getMessage().getChatId().toString(), editMessageText.getChatId());
        assertEquals(query.getMessage().getMessageId(), editMessageText.getMessageId());
        assertTrue(!editMessageText.getText().isEmpty());
        assertNotNull(editMessageText.getReplyMarkup());
        assertInstanceOf(InlineKeyboardMarkup.class, editMessageText.getReplyMarkup());
        assertEquals(3, editMessageText.getReplyMarkup().getKeyboard().stream().mapToInt(List::size).sum());
    }

    @Test
    void processCardReading() {
        CallbackQuery query = new CallbackQuery();
        TaroCard taroCard = createTaroCard();
        query.setData("%s/%s/t/%s/nomin/id/1".formatted(BotFunction.DIVINATION, DivinationType.TARO, DivinationTopic.LOVE));
        query.setMessage(new Message());
        query.getMessage().setChat(new Chat(1L, "type"));
        query.getMessage().setMessageId(1);
        when(layoutRepository.findById(anyInt())).thenReturn(Optional.of(createLayout()));
        when(cardService.getMajorCards()).thenReturn(List.of(taroCard, taroCard, taroCard));
        when(meaningService.findById(anyInt())).thenReturn(createMeaning());

        Response response = sut.process(query);

        assertTrue(response.methods() != null && !response.methods().isEmpty());
        assertEquals(2, response.methods().size());

        assertInstanceOf(DeleteMessage.class, response.methods().get(0));
        DeleteMessage deleteMessage = (DeleteMessage) response.methods().get(0);
        assertEquals(query.getMessage().getChatId().toString(), deleteMessage.getChatId());
        assertEquals(query.getMessage().getMessageId(), deleteMessage.getMessageId());

        assertInstanceOf(SendMessage.class, response.methods().get(1));
        SendMessage sendMessage = (SendMessage) response.methods().get(1);
        assertEquals(query.getMessage().getChatId().toString(), sendMessage.getChatId());
        assertTrue(!sendMessage.getText().isEmpty());
        assertNotNull(sendMessage.getReplyMarkup());
        assertInstanceOf(InlineKeyboardMarkup.class, sendMessage.getReplyMarkup());
        InlineKeyboardMarkup markup = (InlineKeyboardMarkup) sendMessage.getReplyMarkup();
        assertEquals(1, markup.getKeyboard().stream().mapToInt(List::size).sum());
        assertEquals(BotFunction.MENU.getFunctionName(), markup.getKeyboard().get(0).get(0).getCallbackData());

        assertNotNull(response.photos());
        SendMediaGroup photos = response.photos();
        assertEquals(query.getMessage().getChatId().toString(), photos.getChatId());
        assertEquals(2, photos.getMedias().size());
        photos.getMedias().forEach(inputMedia -> assertInstanceOf(InputMediaPhoto.class, inputMedia));
        InputMediaPhoto photo = (InputMediaPhoto) photos.getMedias().get(0);
        assertEquals(taroCard.getImageFileId(), photo.getMedia());
        assertTrue(!photo.getCaption().isEmpty());
        assertTrue(photo.getHasSpoiler());
    }

    @Test
    void processCardReadingWithSimpleLayout() {
        CallbackQuery query = new CallbackQuery();
        TaroCard taroCard = createTaroCard();
        query.setData("%s/%s/t/%s/nomin/id/3".formatted(BotFunction.DIVINATION, DivinationType.TARO, DivinationTopic.LOVE));
        query.setMessage(new Message());
        query.getMessage().setChat(new Chat(1L, "type"));
        query.getMessage().setMessageId(1);
        when(layoutRepository.findById(anyInt())).thenReturn(Optional.of(createLayout()));
        when(cardService.getMajorCards()).thenReturn(List.of(taroCard, taroCard, taroCard));
        when(meaningService.findById(anyInt())).thenReturn(createMeaning());

        Response response = sut.process(query);

        assertTrue(response.methods() != null && !response.methods().isEmpty());
        assertEquals(2, response.methods().size());

        assertInstanceOf(DeleteMessage.class, response.methods().get(0));
        DeleteMessage deleteMessage = (DeleteMessage) response.methods().get(0);
        assertEquals(query.getMessage().getChatId().toString(), deleteMessage.getChatId());
        assertEquals(query.getMessage().getMessageId(), deleteMessage.getMessageId());

        assertInstanceOf(SendMessage.class, response.methods().get(1));
        SendMessage sendMessage = (SendMessage) response.methods().get(1);
        assertEquals(query.getMessage().getChatId().toString(), sendMessage.getChatId());
        assertTrue(!sendMessage.getText().isEmpty());
        assertNotNull(sendMessage.getReplyMarkup());
        assertInstanceOf(InlineKeyboardMarkup.class, sendMessage.getReplyMarkup());
        InlineKeyboardMarkup markup = (InlineKeyboardMarkup) sendMessage.getReplyMarkup();
        assertEquals(1, markup.getKeyboard().stream().mapToInt(List::size).sum());
        assertEquals(BotFunction.MENU.getFunctionName(), markup.getKeyboard().get(0).get(0).getCallbackData());

        assertNotNull(response.photo());
        SendPhoto sendPhoto = response.photo();
        assertEquals(query.getMessage().getChatId().toString(), sendPhoto.getChatId());
        assertEquals(taroCard.getImageFileId(), sendPhoto.getFileField());
        assertTrue(sendPhoto.getCaption().isEmpty());
        assertTrue(sendPhoto.getHasSpoiler());
    }

    @Test
    void processEmptyResponse(){
        CallbackQuery query = new CallbackQuery();
        query.setData("%s/%s/123".formatted(BotFunction.DIVINATION,DivinationType.TARO));

        Response response = sut.process(query);

        assertNull(response.methods());
        assertNull(response.photo());
        assertNull(response.photos());
    }

    private TaroLayout createLayout() {
        return new TaroLayout("Кельтский крест", "Celtic cross",
                "layout", "1.Смысл проблемы&2.Привходящие обстоятельства",
                "один из самых известных и самых старинных раскладов карт Таро",
                "✝", "general", 2);
    }
    private TaroCard createTaroCard() {
        TaroCard taroCard = new TaroCard("Шут", "The Fool", "photo", 0, "description", "features", "symbol", true);
        taroCard.setId(0);
        return taroCard;
    }
    private TaroCardMeaning createMeaning(){
        return new TaroCardMeaning(0, "love", "job", "health", "growth", "spirit", "yseNo", "cardOfTheDay");
    }
}