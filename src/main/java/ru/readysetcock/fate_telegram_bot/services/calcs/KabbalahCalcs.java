package ru.readysetcock.fate_telegram_bot.services.calcs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.readysetcock.fate_telegram_bot.services.domain.KabbalahNumberService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KabbalahCalcs {
    private final KabbalahNumberService numberService;

    public List<Integer> getLettersCount(String text, String firstName, String lastName) {
        String[] words = text.replace("Ваш вопрос: ", "").replaceAll("[^а-яА-Яa-zA-Z0-9 ]", "").trim().split("\\s+");
        List<Integer> lettersCount = new ArrayList<>();
        lettersCount.add(lastName.length());
        lettersCount.add(firstName.length());
        for (String word : words) {
            lettersCount.add(word.length());
        }
        return lettersCount;
    }

    public List<List<Integer>> generateNumericalTriangle(List<Integer> mainRow) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(mainRow);
        final int eighteen = 18;
        while (triangle.size() < mainRow.size()) {
            List<Integer> newRow = new ArrayList<>();
            List<Integer> previousRow = triangle.get(triangle.size() - 1);
            for (int i = 1; i < previousRow.size(); i++) {
                int sum = previousRow.get(i - 1) + previousRow.get(i);
                if (sum > eighteen) {
                    sum -= eighteen;
                }
                newRow.add(sum);
            }
            triangle.add(newRow);
        }
        Collections.reverse(triangle);
        return triangle;
    }

    public int getDivResult(List<List<Integer>> triangle) {
        List<Integer> finalRow = new ArrayList<>();
        for (int i = 0; i < triangle.size(); i++) {
            for (List<Integer> row : triangle) {
                if (i < row.size()) {
                    finalRow.add(row.get(i));
                }
            }
        }
        final int nine = 9;
        int result = 0;
        for (Integer integer : finalRow) {
            result += integer;
            if (integer == nine) {
                break;
            }
        }
        result %= nine;
        if (result == 0) {
            result = nine;
        }
        return result;
    }

    public List<Integer> getNumResult(String firstName, String lastName) {
        int result = 0;
        final int ten = 10;
        for (char letter : firstName.concat(lastName).toCharArray()) {
            result += numberService.findByLetter(letter).getNumValue();
        }
        if (!numberService.exist(result)) {
            int numberOfDigits = String.valueOf(result).length();
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < numberOfDigits; i++) {
                numbers.add(i, (result % ten) * (int) Math.pow(ten, i));
                result /= ten;
            }
            return numbers;
        } else {
            List<Integer> numbers = new ArrayList<>();
            numbers.add(0, result);
            return numbers;
        }
    }
}