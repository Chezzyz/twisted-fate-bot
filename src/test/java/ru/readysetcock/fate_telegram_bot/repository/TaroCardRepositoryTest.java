package ru.readysetcock.fate_telegram_bot.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.readysetcock.fate_telegram_bot.model.domain.TaroCard;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = RepositoryTestConfig.class)
@ActiveProfiles("test")
class TaroCardRepositoryTest {

    @Autowired
    private TaroCardRepository repository;

    @Test
    void saveExampleEntityTest() {
        TaroCard taroCard = new TaroCard("Шут","The Fool","classpath:images/Fool.jpg",0,"description","features",
                "symbol",true);

        Integer id = repository.save(taroCard).getId();
        Optional<TaroCard> found = repository.findById(id);

        Assertions.assertTrue(repository.existsById(id));
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(taroCard, found.get());
    }
}
