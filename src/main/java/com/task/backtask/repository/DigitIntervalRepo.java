package com.task.backtask.repository;

import com.task.backtask.entity.DigitInterval;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DigitIntervalRepo extends CrudRepository<DigitInterval, Integer> {

    @Query("""
            SELECT v
            FROM DigitInterval v
            WHERE v.start = (SELECT MIN(k.start) FROM DigitInterval k)
            AND v.end = (SELECT MIN(k.end) FROM DigitInterval k)
            ORDER BY v.id
            LIMIT 1
            """)
    Optional<DigitInterval> findMinInterval();
}
