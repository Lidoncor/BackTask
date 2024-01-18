package com.task.backtask.repository;

import com.task.backtask.entity.LetterInterval;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterIntervalRepo extends CrudRepository<LetterInterval, Integer> {
}
