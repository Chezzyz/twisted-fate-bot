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
import ru.readysetcock.fate_telegram_bot.repository.KabbalisticNumberRepository;
import ru.readysetcock.fate_telegram_bot.repository.UserRepository;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommand;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;

@Service
@RequiredArgsConstructor
public class KabbalahProcessor implements BotCommandProcessor, BotFunctionProcessor {

    private final UserRepository userRepository;
    private final KabbalisticNumberRepository numberRepository;

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

    private Response getKabbalahDiv(CallbackQuery query) {
        Message message = query.getMessage();
        List<Integer> mainRow = getLettersCount(message);
        List<List<Integer>> triangle = generateNumericalTriangle(mainRow);
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(),
                numberRepository.findById(getDivResult(triangle)).orElseThrow().getDivinationMeaning(),
                InlineKeyboardBuilder.createKeyboardOf(rowOf(button("⬅ Назад", BotFunction.KABBALAH.getFunctionName())))));
    }

    private Response getKabbalahNum(CallbackQuery query) {
        Message message = query.getMessage();
        User user = userRepository.findByTgUserId(query.getMessage().getChatId());
        int[] numbers = getNumResult(user.getFirstName(), user.getLastName());
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), getNumResponseText(numbers, user),
                InlineKeyboardBuilder.createKeyboardOf(rowOf(button("⬅ Назад", BotFunction.KABBALAH.getFunctionName())))));
    }

    private List<Integer> getLettersCount(Message message) {
        String[] words = message.getText().replace("Ваш вопрос: ", "").replaceAll("[^а-яА-Яa-zA-Z0-9 ]", "").trim().split("\\s+");
        List<Integer> lettersCount = new ArrayList<>();
        User user = userRepository.findByTgUserId(message.getChatId());
        lettersCount.add(user.getLastName().length());
        lettersCount.add(user.getFirstName().length());
        for (String word : words) {
            lettersCount.add(word.length());
        }
        return lettersCount;
    }

    private List<List<Integer>> generateNumericalTriangle(List<Integer> mainRow) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(mainRow);
        while (triangle.size() < mainRow.size()) {
            List<Integer> newRow = new ArrayList<>();
            List<Integer> lastRow = triangle.get(triangle.size() - 1);
            for (int i = 1; i < lastRow.size(); i++) {
                int sum = lastRow.get(i - 1) + lastRow.get(i);
                if (sum > 18) {
                    sum -= 18;
                }
                newRow.add(sum);
            }
            triangle.add(newRow);
        }
        Collections.reverse(triangle);
        return triangle;
    }

    private int getDivResult(List<List<Integer>> triangle) {
        List<Integer> finalRow = new ArrayList<>();
        for (int i = 0; i < triangle.size(); i++) {
            for (List<Integer> integers : triangle) {
                if (i < integers.size()) {
                    finalRow.add(integers.get(i));
                }
            }
        }
        int result = 0;
        for (Integer integer : finalRow) {
            result += integer;
            if (integer == 9) {
                break;
            }
        }
        result %= 9;
        if (result == 0) {
            result = 9;
        }
        return result;
    }

    private int[] getNumResult(String firstName, String lastName) {
        int result = 0;
        for (char letter : firstName.concat(lastName).toCharArray()) {
            result += numberRepository.findByLettersContaining(letter).getNumValue();
        }
        if (numberRepository.existsByNumValue(result).equals(Boolean.FALSE)) {
            int numberOfDigits = String.valueOf(result).length();
            int[] numbers = new int[numberOfDigits];
            for (int i = 0; i < numberOfDigits; i++) {
                numbers[i] = (result % 10) * (int) Math.pow(10, i);
                result /= 10;
            }
            return numbers;
        } else {
            int[] numbers = new int[1];
            numbers[0] = result;
            return numbers;
        }
    }

    private String getNumResponseText(int[] numbers, User user) {
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
                        """.formatted(number, numberRepository.findById(number).orElseThrow().getMeaning()));
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

    private Response getQuestion(CallbackQuery query) {
        Message message = query.getMessage();
        User user = userRepository.findByTgUserId(query.getMessage().getChatId());
        user.setState(0);
        userRepository.save(user);
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), BotState.QUESTION.getText(), InlineKeyboardBuilder.createKeyboardOf(
                rowOf(button("⬅ Назад", BotFunction.KABBALAH.getFunctionName())))));
    }

    private InlineKeyboardMarkup getKeyboardOfKabbalahTypes() {
        return InlineKeyboardBuilder.createKeyboardOf(
                rowOf(button("Каббалистическое гадание", "🔮", BotFunction.KABBALAH.toString().concat("/div"))),
                rowOf(button("Каббалистическая нумерология", "\uD83D\uDD22", BotFunction.KABBALAH.toString().concat("/num"))),
                rowOf(button("⬅ Назад", BotFunction.MENU.getFunctionName())));
    }
}