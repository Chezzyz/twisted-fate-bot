package ru.readysetcock.fate_telegram_bot.services.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.readysetcock.fate_telegram_bot.model.domain.KabbalisticNumber;
import ru.readysetcock.fate_telegram_bot.repository.KabbalisticNumberRepository;

@Service
@RequiredArgsConstructor
public class KabbalahNumberService {
    private final KabbalisticNumberRepository repository;

    public KabbalisticNumber findById(Integer id){
        return repository.findById(id).orElseThrow();
    }

    public KabbalisticNumber findByLetter(char letter){
        return repository.findByLettersContaining(letter);
    }

    public boolean exist(int value){
        return repository.existsByNumValue(value);
    }
}