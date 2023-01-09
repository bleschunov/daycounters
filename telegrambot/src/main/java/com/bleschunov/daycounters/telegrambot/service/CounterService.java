package com.bleschunov.daycounters.telegrambot.service;

import com.bleschunov.daycounters.telegrambot.dto.AppUserDto;
import com.bleschunov.daycounters.telegrambot.dto.CounterDto;
import com.bleschunov.daycounters.telegrambot.exception.BusinessException;
import com.bleschunov.daycounters.telegrambot.util.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.telegram.telegrambots.meta.api.objects.Update;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class CounterService {
    private final WebClient webClient;
    private final Messages messages;

    public void createCounter(Update update) {
        String title = getParam(update, "This command must contain param: <title>.\n" +
                "Example: /createcounter drink water every day");

        CounterDto counterDto = CounterDto
                .builder()
                .ownerTelegramId(update.getMessage().getFrom().getId())
                .title(title)
                .build();

        try {
            webClient
                    .post()
                    .uri("/counter")
                    .body(Mono.just(counterDto), AppUserDto.class)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            throw new BusinessException("You are not registered. Please print /start.", e);
        }
    }

    public List<CounterDto> getAllAvailableCounters(Update update) {
        return webClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                            .path("/counter")
                            .queryParam("telegram_id", update.getMessage().getFrom().getId())
                            .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CounterDto>>() {})
                .block();
    }

    public long getCounterValue(Update update) {
        String counterId = getParam(update, "This command must contain param: <id>.\n" +
                "Example: /getcountervalue 123");

        return webClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .pathSegment("counter", counterId)
                                .queryParam("telegram_id", update.getMessage().getFrom().getId())
                                .build()
                )
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

    public void deleteCounter(Update update) {
        String counterId = getParam(update, "This command must contain param: <id>.\n" +
                "Example: /deletecounter 123");

        webClient
                .delete()
                .uri(uriBuilder ->
                        uriBuilder
                                .pathSegment("counter", counterId)
                                .queryParam("telegram_id", update.getMessage().getFrom().getId())
                                .build()
                )
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

    public void resetCounter(Update update) {
        String counterId = getParam(update, "This command must contain param: <id>.\n" +
                "Example: /resetcounter 123");

        webClient
                .put()
                .uri(uriBuilder ->
                        uriBuilder
                                .pathSegment("counter", "reset", counterId)
                                .queryParam("telegram_id", update.getMessage().getFrom().getId())
                                .build()
                )
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

    private String getParam(Update update, String exceptionMessage) {
        String param = messages.getCommandParam(update.getMessage().getText());
        if (param.length() == 0) {
            throw new BusinessException(exceptionMessage);
        }
        return param;
    }
}
