package com.bleschunov.daycounters.rest.repository;

import com.bleschunov.daycounters.rest.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByTelegramId(long telegramId);
    boolean existsByTelegramId(long telegramId);
}
