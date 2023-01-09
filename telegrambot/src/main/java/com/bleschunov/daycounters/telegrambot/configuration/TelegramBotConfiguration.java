package com.bleschunov.daycounters.telegrambot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Bleschunov Dmitry
 */
@Configuration
public class TelegramBotConfiguration {

    @Value("${rest.uri}")
    private String restUri;

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(restUri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
