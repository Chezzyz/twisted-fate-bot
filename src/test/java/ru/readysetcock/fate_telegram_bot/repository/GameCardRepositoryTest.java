package ru.readysetcock.fate_telegram_bot.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.readysetcock.fate_telegram_bot.model.domain.GameCard;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = RepositoryTestConfig.class)
@ActiveProfiles("test")
class GameCardRepositoryTest {

    @Autowired
    private GameCardRepository repository;

    @Test
    void saveGameCardTest() {
        GameCard card = new GameCard("rusName", "engName", "imagePath", "example");

        Integer id = repository.save(card).getId();
        Optional<GameCard> found = repository.findById(id);

        Assertions.assertTrue(repository.existsById(id));
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(card, found.get());
    }
}
