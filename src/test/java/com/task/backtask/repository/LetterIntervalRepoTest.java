package com.task.backtask.repository;

import com.task.backtask.entity.LetterInterval;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class LetterIntervalRepoTest {

    @Autowired
    private LetterIntervalRepo letterIntervalRepo;

    @AfterEach
    void cleanUp() {
        letterIntervalRepo.deleteAll();
    }

    @Test
    void findMinIntervalTest() {
        letterIntervalRepo.saveAll(
                List.of(
                        new LetterInterval("a", "j"),
                        new LetterInterval("r", "z")
                )
        );

        var returned = letterIntervalRepo.findMinInterval();

        assertThat(returned)
                .isPresent()
                .get()
                .returns("a", from(LetterInterval::getStart))
                .returns("j", from(LetterInterval::getEnd));
    }

    @Test
    void unableToFindMinIntervalWithoutEmptyDbTest() {
        letterIntervalRepo.saveAll(
                List.of(
                        new LetterInterval("a", "f"),
                        new LetterInterval("r", "e")
                )
        );

        assertThatThrownBy(() -> letterIntervalRepo.findMinInterval().orElseThrow())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void unableToFindMinIntervalWithEmptyDbTest() {
        assertThatThrownBy(() -> letterIntervalRepo.findMinInterval().orElseThrow())
                .isInstanceOf(NoSuchElementException.class);
    }
}