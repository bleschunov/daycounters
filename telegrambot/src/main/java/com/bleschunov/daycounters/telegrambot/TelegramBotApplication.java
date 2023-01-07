package com.bleschunov.daycounters.telegrambot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bleschunov Dmitry
 */
@SpringBootApplication
@Slf4j
public class TelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotApplication.class);
    }
}
