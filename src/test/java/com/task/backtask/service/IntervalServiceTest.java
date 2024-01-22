package com.task.backtask.service;

import com.task.backtask.entity.DigitInterval;
import com.task.backtask.entity.LetterInterval;
import com.task.backtask.repository.DigitIntervalRepo;
import com.task.backtask.repository.LetterIntervalRepo;
import com.task.backtask.types.Kind;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IntervalServiceTest {

    @Mock
    private DigitIntervalRepo digitIntervalRepo;

    @Mock
    private LetterIntervalRepo letterIntervalRepo;

    @InjectMocks
    private IntervalService intervalService;

    @Test
    void mergeDigitsTest() {
        var returned = intervalService.merge(
                Arrays.asList(
                        Arrays.asList(1, 4),
                        Arrays.asList(3, 6),
                        Arrays.asList(8, 10)
                ),
                Kind.DIGITS
        );

        var expected = List.of(
                List.of(1, 6),
                List.of(8, 10)
        ).toString();

        assertThat(returned).isEqualTo(expected);

        verify(digitIntervalRepo, times(1)).saveAll(any());
    }

    @Test
    void mergeDigitsClassCastExceptionTest() {
        assertThatThrownBy(() -> intervalService.merge(Arrays.asList(Arrays.asList(1, 4)), Kind.LETTERS))
                .isInstanceOf(ClassCastException.class);
    }

    @Test
    void mergeLettersTest() {
        var returned = intervalService.merge(
                Arrays.asList(
                        Arrays.asList("a", "f"),
                        Arrays.asList("d", "j"),
                        Arrays.asList("r", "z")
                ),
                Kind.LETTERS
        );

        var expected = List.of(
                List.of("a", "j"),
                List.of("r", "z")
        ).toString();

        assertThat(returned).isEqualTo(expected);

        verify(letterIntervalRepo, times(1)).saveAll(any());
    }

    @Test
    void mergeLettersIllegalArgumentExceptionTest() {
        assertThatThrownBy(() -> intervalService.merge(Arrays.asList(Arrays.asList("aaa", "bbb")), Kind.LETTERS))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void minDigitsTest() {
        var expected = new DigitInterval(1, 6);

        when(digitIntervalRepo.findMinInterval()).thenReturn(Optional.of(expected));

        assertThat(intervalService.min(Kind.DIGITS)).isEqualTo(expected.toString());
    }

    @Test
    void minLettersTest() {
        var expected = new LetterInterval("a", "j");

        when(letterIntervalRepo.findMinInterval()).thenReturn(Optional.of(expected));

        assertThat(intervalService.min(Kind.LETTERS)).isEqualTo(expected.toString());
    }
}