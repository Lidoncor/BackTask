package com.task.backtask.service;

import com.task.backtask.entity.DigitInterval;
import com.task.backtask.entity.LetterInterval;
import com.task.backtask.repository.DigitIntervalRepo;
import com.task.backtask.repository.LetterIntervalRepo;
import com.task.backtask.types.Kind;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IntervalService {

    private final DigitIntervalRepo digitIntervalRepo;

    private final LetterIntervalRepo letterIntervalRepo;

    public String merge(List<List<?>> request, Kind kind) {
        return switch (kind) {
            case DIGITS -> digitsMerge(request);
            case LETTERS -> lettersMerge(request);
        };
    }

    public String min(Kind kind) {
        return switch (kind) {
            case DIGITS -> digitIntervalRepo.findMinInterval().orElseThrow().toString();
            case LETTERS -> letterIntervalRepo.findMinInterval().orElseThrow().toString();
        };
    }

    private String digitsMerge(List<List<?>> request) {
        var intervals = request
                .stream()
                .map(i -> (List<Integer>) i)
                .sorted(Comparator.comparingInt(o -> o.get(0)))
                .toList();

        var output = new ArrayList<>(List.of(intervals.get(0)));

        intervals
                .stream()
                .skip(1)
                .forEachOrdered(i -> {
                    var last = output.get(output.size() - 1);
                    var lastEnd = last.get(1);

                    var currentStart = i.get(0);
                    var currentEnd = i.get(1);

                    if (lastEnd >= currentStart) {
                        last.set(1, Math.max(lastEnd, currentEnd));
                    } else {
                        output.add(i);
                    }
                });

        digitIntervalRepo.saveAll(
                output
                        .stream()
                        .map(i -> new DigitInterval(i.get(0), i.get(1)))
                        .toList()
        );

        return output.toString();
    }

    private String lettersMerge(List<List<?>> request) {
        var intervals = request
                .stream()
                .map(i -> {
                    if (i.get(0).toString().length() == 1 && i.get(1).toString().length() == 1) {
                        return (List<String>) i;
                    } else {
                        throw new IllegalArgumentException("Must be single-letter objects");
                    }
                })
                .sorted(Comparator.comparing(o -> o.get(0).charAt(0)))
                .toList();

        var output = new ArrayList<>(List.of(intervals.get(0)));

        intervals
                .stream()
                .skip(1)
                .forEachOrdered(i -> {
                    var last = output.get(output.size() - 1);
                    var lastEnd = last.get(1).charAt(0);

                    var currentStart = i.get(0).charAt(0);
                    var currentEnd = i.get(1).charAt(0);

                    if (lastEnd >= currentStart) {
                        last.set(1, String.valueOf((char) Math.max(lastEnd, currentEnd)));
                    } else {
                        output.add(i);
                    }
                });

        letterIntervalRepo.saveAll(
                output
                        .stream()
                        .map(i -> new LetterInterval(i.get(0), i.get(1)))
                        .toList()
        );

        return output.toString();
    }

}
