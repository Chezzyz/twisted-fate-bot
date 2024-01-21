package ru.readysetcock.fate_telegram_bot.services.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCard;
import ru.readysetcock.fate_telegram_bot.repository.TaroCardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaroCardService {
    private final TaroCardRepository repository;

    public List<TaroCard> getMajorCards() {
        return repository.findTaroCardsByMajorIsTrue();
    }

    public TaroCard findById(int id) {
        return repository.findTaroCardById(id);
    }

    public Iterable<TaroCard> findAll() {
        return repository.findAll();
    }

    public boolean existById(int id) {
        return repository.existsById(id);
    }
}