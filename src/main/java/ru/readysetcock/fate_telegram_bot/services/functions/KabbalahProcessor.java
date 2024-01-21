package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.User;
import ru.readysetcock.fate_telegram_bot.services.calcs.KabbalahCalcs;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommand;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;
import ru.readysetcock.fate_telegram_bot.services.domain.KabbalahNumberService;
import ru.readysetcock.fate_telegram_bot.services.domain.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;

@Service
@RequiredArgsConstructor
public class KabbalahProcessor implements BotCommandProcessor, BotFunctionProcessor {

    private final UserService userService;
    private final KabbalahNumberService numberService;
    private final KabbalahCalcs calcs;

    @Override
    public BotCommand getCommand() {
        return BotCommand.KABBALAH;
    }

    @Override
    public BotFunction getFunction() {
        return BotFunction.KABBALAH;
    }

    @Override
    public Response process(Message message) {
        return sendButtonsWithTypes(message);
    }

    @Override
    public Response process(CallbackQuery query) {
        String data = query.getData();
        if (data.equals(BotFunction.KABBALAH.toString())) {
            return editToButtonsWithTypes(query);
        } else if (data.contains("/div")) {
            return getQuestion(query);
        } else if (data.contains("/question")) {
            return getKabbalahDiv(query);
        } else if (data.contains("/num")) {
            return getKabbalahNum(query);
        }
        return new Response();
    }

    private Response getQuestion(CallbackQuery query) {
        Message message = query.getMessage();
        User user = userService.findByUserId(query.getMessage().getChatId());
        user.setState(BotState.QUESTION.getStateName());
        userService.save(user);
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), BotState.QUESTION.getText(), InlineKeyboardBuilder.createKeyboardOf(
                rowOf(button("⬅ Назад", BotFunction.KABBALAH.getFunctionName())))));
    }

    private Response getKabbalahDiv(CallbackQuery query) {
        Message message = query.getMessage();
        User user = userService.findByUserId(message.getChatId());
        List<Integer> mainRow = calcs.getLettersCount(message.getText(), user.getFirstName(), user.getLastName());
        List<List<Integer>> triangle = calcs.generateNumericalTriangle(mainRow);
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(),
                numberService.findById(calcs.getDivResult(triangle)).getDivinationMeaning(),
                InlineKeyboardBuilder.createKeyboardOf(rowOf(button("⬅ Назад", BotFunction.KABBALAH.getFunctionName())))));
    }

    private Response getKabbalahNum(CallbackQuery query) {
        Message message = query.getMessage();
        User user = userService.findByUserId(query.getMessage().getChatId());
        List<Integer> numbers = calcs.getNumResult(user.getFirstName(), user.getLastName());
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), getNumResponseText(numbers, user),
                InlineKeyboardBuilder.createKeyboardOf(rowOf(button("⬅ Назад", BotFunction.KABBALAH.getFunctionName())))));
    }

    private String getNumResponseText(List<Integer> numbers, User user) {
        List<String> descriptionList = new ArrayList<>();
        descriptionList.add("""
                <b>Имя:</b> %s
                <b>Фамилия:</b> %s
                                
                <b>Значение чисел</b>:
                                
                """.formatted(user.getFirstName(), user.getLastName()));
        for (int number : numbers) {
            if (!String.valueOf(number).equals("0")) {
                descriptionList.add("""
                        <b>%s</b> - %s
                        """.formatted(number, numberService.findById(number).getMeaning()));
            }
        }
        return String.join("", descriptionList);
    }

    private Response sendButtonsWithTypes(Message message) {
        return new Response(BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(), "Выберите тип каббалистического ремесла",
                getKeyboardOfKabbalahTypes()));
    }

    private Response editToButtonsWithTypes(CallbackQuery query) {
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Выберите тип каббалистического ремесла", getKeyboardOfKabbalahTypes()));
    }

    private InlineKeyboardMarkup getKeyboardOfKabbalahTypes() {
        return InlineKeyboardBuilder.createKeyboardOf(
                rowOf(button("Каббалистическое гадание", "🔮", BotFunction.KABBALAH.toString().concat("/div"))),
                rowOf(button("Каббалистическая нумерология", "\uD83D\uDD22", BotFunction.KABBALAH.toString().concat("/num"))),
                rowOf(button("⬅ Назад", BotFunction.MENU.getFunctionName())));
    }
}