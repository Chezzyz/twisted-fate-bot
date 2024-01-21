package ru.readysetcock.fate_telegram_bot.services.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCardOfTheDay;
import ru.readysetcock.fate_telegram_bot.repository.TaroCardOfTheDayRepository;

@Service
@RequiredArgsConstructor
public class TaroCardOfTheDayService {
    private final TaroCardOfTheDayRepository repository;

    public boolean existById(Long id){
        return repository.existsById(id);
    }

    public TaroCardOfTheDay findById(Long id){
        return repository.findTaroCardOfTheDayByUserId(id);
    }

    public TaroCardOfTheDay save(TaroCardOfTheDay taroCardOfTheDay){
        return repository.save(taroCardOfTheDay);
    }
}