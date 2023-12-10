package ru.readysetcock.fate_telegram_bot.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.readysetcock.fate_telegram_bot.model.domain.EnergyStone;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = RepositoryTestConfig.class)
@ActiveProfiles("test")
public class StoneEnergyRepositoryTest {

    @Autowired
    private StoneEnergyRepository repository;

    @Test
    public void saveStoneEnergyTest() {
        EnergyStone stoneEnergy = new EnergyStone("rusName", "engName", "imagePath", "", "");
        repository.save(stoneEnergy);
        Optional<EnergyStone> found = repository.findById(stoneEnergy.getId());
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(stoneEnergy, found.get());
    }
}