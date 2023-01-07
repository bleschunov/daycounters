package com.bleschunov.daycounters.telegrambot.service;

import com.bleschunov.daycounters.telegrambot.dto.AppUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import reactor.core.publisher.Mono;

/**
 * @author Bleschunov Dmitry
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AppUserService {
    private final WebClient webClient;

    public void registerTelegramUser(Update update) {
        User telegramUser = update.getMessage().getFrom();

        AppUserDto appUserDto = AppUserDto
                .builder()
                .telegramUsername(telegramUser.getUserName())
                .telegramId(telegramUser.getId())
                .firstname(telegramUser.getFirstName())
                .lastname(telegramUser.getLastName())
                .build();

        webClient
                .post()
                .uri("/user")
                .body(Mono.just(appUserDto), AppUserDto.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
