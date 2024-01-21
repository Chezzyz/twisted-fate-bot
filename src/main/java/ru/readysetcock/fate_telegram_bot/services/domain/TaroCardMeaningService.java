package ru.readysetcock.fate_telegram_bot.services.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCardMeaning;
import ru.readysetcock.fate_telegram_bot.repository.TaroCardMeaningRepository;

@Service
@RequiredArgsConstructor
public class TaroCardMeaningService {
    private final TaroCardMeaningRepository repository;

    public TaroCardMeaning findById(int id){
        return repository.findTaroCardMeaningByCardId(id);
    }
}
