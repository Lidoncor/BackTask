package com.task.backtask.repository;

import com.task.backtask.entity.LetterInterval;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LetterIntervalRepo extends CrudRepository<LetterInterval, Integer> {

    @Query("""
            SELECT v
            FROM LetterInterval v
            WHERE v.start = (SELECT MIN(k.start) FROM LetterInterval k)
            AND v.end = (SELECT MIN(k.end) FROM LetterInterval k)
            ORDER BY v.id
            LIMIT 1
            """)
    Optional<LetterInterval> findMinInterval();
}
