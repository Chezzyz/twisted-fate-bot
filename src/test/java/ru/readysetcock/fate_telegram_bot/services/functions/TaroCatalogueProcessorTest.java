package ru.readysetcock.fate_telegram_bot.services.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCard;
import ru.readysetcock.fate_telegram_bot.repository.TaroCardRepository;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommand;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaroCatalogueProcessorTest {

    @Mock
    private TaroCardRepository repository;

    private TaroCatalogueProcessor sut;

    @BeforeEach
    public void init() {
        sut = new TaroCatalogueProcessor(repository);
    }

    @Test
    void returnTarosAsFunc() {
        assertEquals(BotFunction.TAROS, sut.getFunction());
    }

    @Test
    void getCommand() {
        assertEquals(BotCommand.TAROS, sut.getCommand());
    }

    @Test
    void process() {
        Message message = new Message();
        message.setChat(new Chat(1L, "type"));
        when(repository.findAll()).thenReturn(List.of(createTaroCard(), createTaroCard()));

        Response response = sut.process(message);

        assertTrue(response.methods() != null && !response.methods().isEmpty());
        assertEquals(1, response.methods().size());
        assertInstanceOf(SendMessage.class, response.methods().get(0));
        SendMessage method = (SendMessage) response.methods().get(0);
        assertTrue(!method.getText().isEmpty());
        assertEquals(message.getChatId().toString(), method.getChatId());
        assertNotNull(method.getReplyMarkup());
        assertInstanceOf(InlineKeyboardMarkup.class, method.getReplyMarkup());
        InlineKeyboardMarkup markup = (InlineKeyboardMarkup) method.getReplyMarkup();
        assertEquals(3, markup.getKeyboard().stream().mapToInt(List::size).sum());
    }

    @Test
    void processEditToButtonsWithCards() {
        CallbackQuery query = new CallbackQuery();
        query.setData(BotFunction.TAROS.getFunctionName());
        query.setMessage(new Message());
        query.getMessage().setChat(new Chat(1L, "type"));
        query.getMessage().setMessageId(1);
        when(repository.findAll()).thenReturn(List.of(createTaroCard(), createTaroCard()));

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
    void processSendTaroCard() {
        int taroCardId = 1;
        TaroCard taroCard = createTaroCard();
        when(repository.findById(taroCardId)).thenReturn(Optional.of(taroCard));
        CallbackQuery query = new CallbackQuery();
        query.setMessage(new Message());
        query.getMessage().setChat(new Chat(1L, "type"));
        query.getMessage().setMessageId(1);
        query.setData(BotFunction.TAROS.getFunctionName() + "/id/" + taroCardId);
        query.setId("1");

        Response response = sut.process(query);

        assertTrue(response.methods() != null && !response.methods().isEmpty());
        assertEquals(1, response.methods().size());
        assertInstanceOf(AnswerCallbackQuery.class, response.methods().get(0));

        assertNotNull(response.photo());
        assertEquals(query.getMessage().getChatId().toString(), response.photo().getChatId());
        assertEquals("photo", taroCard.getImageFileId());
        assertTrue(!response.photo().getCaption().isEmpty());
        assertNotNull(response.photo().getReplyMarkup());
        assertInstanceOf(InlineKeyboardMarkup.class, response.photo().getReplyMarkup());
        InlineKeyboardMarkup markup = (InlineKeyboardMarkup) response.photo().getReplyMarkup();
        assertEquals(1, markup.getKeyboard().stream().mapToInt(List::size).sum());
        assertEquals("%s/delete".formatted(BotFunction.TAROS.getFunctionName()), markup.getKeyboard().get(0).get(0).getCallbackData());

        assertEquals(query.getId(), ((AnswerCallbackQuery) response.methods().get(0)).getCallbackQueryId());
    }

    @Test
    void processDeleteCard() {
        CallbackQuery query = new CallbackQuery();
        query.setMessage(new Message());
        query.getMessage().setChat(new Chat(1L, "type"));
        query.getMessage().setMessageId(1);
        query.setData(BotFunction.TAROS.getFunctionName() + "/delete");

        Response response = sut.process(query);

        assertTrue(response.methods() != null && !response.methods().isEmpty());
        assertEquals(1, response.methods().size());
        assertInstanceOf(DeleteMessage.class, response.methods().get(0));
        DeleteMessage deleteMessage = (DeleteMessage) response.methods().get(0);
        assertEquals(query.getMessage().getChatId().toString(), deleteMessage.getChatId());
        assertEquals(query.getMessage().getMessageId(), deleteMessage.getMessageId());
    }

    @Test
    void returnEmptyResponse() {
        CallbackQuery query = new CallbackQuery();
        query.setData(BotFunction.TAROS.getFunctionName() + "123");

        Response response = sut.process(query);

        assertNull(response.methods());
        assertNull(response.photo());
        assertNull(response.photos());
    }

    private TaroCard createTaroCard() {
        return new TaroCard("Шут", "The Fool", "photo", 0, "description", "features", "symbol",true);
    }
}