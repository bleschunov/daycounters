package com.bleschunov.daycounters.telegrambot.service;

import com.bleschunov.daycounters.telegrambot.dto.AppUserDto;
import com.bleschunov.daycounters.telegrambot.dto.CounterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class CounterService {
    private final WebClient webClient;

    public void createCounter(Update update) {
        String messageText = update.getMessage().getText();
        String title = messageText.substring(messageText.indexOf(' '));

        CounterDto counterDto = CounterDto
                .builder()
                .ownerTelegramId(update.getMessage().getFrom().getId())
                .title(title)
                .build();

        webClient
                .post()
                .uri("/counter")
                .body(Mono.just(counterDto), AppUserDto.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
