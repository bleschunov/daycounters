package com.bleschunov.daycounters.repository;

import com.bleschunov.daycounters.model.Counter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface CounterRepository extends CrudRepository<Counter, Long> {
}
