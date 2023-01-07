package com.bleschunov.daycounters.service;

import com.bleschunov.daycounters.dto.AppUserDto;
import com.bleschunov.daycounters.exception.EntityNotUniqueException;
import com.bleschunov.daycounters.mapper.AppUserMapper;
import com.bleschunov.daycounters.model.AppUser;
import com.bleschunov.daycounters.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserMapper authMapper;
    private final AppUserRepository appUserRepository;

    public void createUser(AppUserDto appUserDto) {
        if (appUserRepository.existsByTelegramId(appUserDto.getTelegramId())) {
            throw new EntityNotUniqueException(String.format(
                    "User with telegramId = %d already exists.",
                    appUserDto.getTelegramId()
            ));
        }
        AppUser transientAppUser = authMapper.toEntity(appUserDto);
        appUserRepository.save(transientAppUser);
    }
}
