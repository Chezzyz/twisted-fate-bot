package ru.readysetcock.fate_telegram_bot.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.readysetcock.fate_telegram_bot.model.domain.ExampleEntity;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = RepositoryTestConfig.class)
@ActiveProfiles("test")
public class ExampleRepositoryTest {

    @Autowired
    private ExampleRepository repository;

    @Test
    public void saveExampleEntityTest() {
        ExampleEntity entity = new ExampleEntity("rusName", "engName", "imagePath", "example");

        Integer id = repository.save(entity).getId();
        Optional<ExampleEntity> found = repository.findById(id);

        Assertions.assertTrue(repository.existsById(id));
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(entity, found.get());
    }
}
