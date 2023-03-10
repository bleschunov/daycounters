package com.bleschunov.daycounters.rest.repository;

import com.bleschunov.daycounters.rest.model.Counter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface CounterRepository extends CrudRepository<Counter, Long> {
    List<Counter> findAllByOwnerTelegramId(long telegramId);
}
