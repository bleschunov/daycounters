package com.bleschunov.daycounters.telegrambot.service;

import com.bleschunov.daycounters.telegrambot.dto.AppUserDto;
import com.bleschunov.daycounters.telegrambot.dto.CounterDto;
import com.bleschunov.daycounters.telegrambot.util.Messages;
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
    private final Messages messages;

    public void createCounter(Update update) {
        String title = messages.getCommandParam(update.getMessage().getText());

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

    public long getCounterValue(Update update) {
        String title = messages.getCommandParam(update.getMessage().getText());

        return webClient
                .get()
                .uri("/counter/" + title)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }
}
