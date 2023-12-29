package ru.readysetcock.fate_telegram_bot.services.functions.divination;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.services.functions.BotFunction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DivinationProcessorTest {

    @Mock
    private DivinationSubprocessor subprocessor;

    private DivinationProcessor sut;


    @BeforeEach
    public void init(){
        when(subprocessor.getDivinationType()).thenReturn(DivinationType.CARDS);
        sut = new DivinationProcessor(List.of(subprocessor));
    }

    @Test
    void testProcessMessage(){
        Message message = new Message();
        message.setChat((new Chat(1L, "type")));

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
        assertEquals(5, markup.getKeyboard().stream().mapToInt(List::size).sum());
    }

    @Test
    void testProcessCallbackQueryDivination(){
        CallbackQuery query = new CallbackQuery();
        query.setData(BotFunction.DIVINATION.toString());
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
        assertEquals(5, editMessageText.getReplyMarkup().getKeyboard().stream().mapToInt(List::size).sum());
    }

    @Test
    void testProcessCallbackQuerySubprocessorIf() {
        CallbackQuery query = new CallbackQuery();
        query.setData(BotFunction.DIVINATION.toString().concat("/cards"));
        when(subprocessor.process(query)).thenReturn(new Response());

        Response response = sut.process(query);

        assertNotNull(response);
        assertEquals(subprocessor.getDivinationType().toString(),query.getData().split("/")[1]);
        verify(subprocessor).process(query);
    }

    @Test
    void testProcessCallbackQuerySubprocessorElse(){
        CallbackQuery query = new CallbackQuery();
        query.setId("1");
        query.setData(BotFunction.DIVINATION.toString().concat("/unknown"));

        Response response = sut.process(query);

        assertTrue(response.methods() != null && !response.methods().isEmpty());
        assertEquals(1, response.methods().size());
        assertInstanceOf(AnswerCallbackQuery.class,response.methods().get(0));
        AnswerCallbackQuery callbackQuery = (AnswerCallbackQuery) response.methods().get(0);
        assertEquals(query.getId(),callbackQuery.getCallbackQueryId());
        assertTrue(!callbackQuery.getText().isEmpty());
        assertEquals(false, callbackQuery.getShowAlert());
    }

    @Test
    void returnEmptyResponse(){
        CallbackQuery query = new CallbackQuery();
        query.setData(BotFunction.DIVINATION.toString().concat("123"));

        Response response = sut.process(query);

        assertNull(response.methods());
        assertNull(response.photo());
        assertNull(response.photos());
    }
}