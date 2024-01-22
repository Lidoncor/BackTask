package com.task.backtask.repository;

import com.task.backtask.entity.DigitInterval;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class DigitIntervalRepoTest {

    @Autowired
    private DigitIntervalRepo digitIntervalRepo;

    @AfterEach
    void cleanUp() {
        digitIntervalRepo.deleteAll();
    }

    @Test
    void findMinIntervalTest() {
        digitIntervalRepo.saveAll(
                List.of(
                        new DigitInterval(1, 6),
                        new DigitInterval(8, 10)
                )
        );

        var returned = digitIntervalRepo.findMinInterval();

        assertThat(returned.get())
                .returns(1, from(DigitInterval::getStart))
                .returns(6, from(DigitInterval::getEnd));
    }

    @Test
    void unableToFindMinIntervalWithoutEmptyDbTest() {
        digitIntervalRepo.saveAll(
                List.of(
                        new DigitInterval(1, 5),
                        new DigitInterval(8, 4)
                )
        );

        assertThatThrownBy(() -> digitIntervalRepo.findMinInterval().orElseThrow())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void unableToFindMinIntervalWithEmptyDbTest() {
        assertThatThrownBy(() -> digitIntervalRepo.findMinInterval().orElseThrow())
                .isInstanceOf(NoSuchElementException.class);
    }
}