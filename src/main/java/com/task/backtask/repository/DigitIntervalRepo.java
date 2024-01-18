package com.task.backtask.repository;

import com.task.backtask.entity.DigitInterval;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitIntervalRepo extends CrudRepository<DigitInterval, Integer> {
}
