package com.bleschunov.daycounters.telegrambot.controller;

import com.bleschunov.daycounters.telegrambot.enums.Command;
import com.bleschunov.daycounters.telegrambot.service.AppUserService;
import com.bleschunov.daycounters.telegrambot.service.CounterService;
import com.bleschunov.daycounters.telegrambot.util.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Bleschunov Dmitry
 */
@Component
@RequiredArgsConstructor
public class Dispatcher {
    private final Messages messages;
    private final AppUserService appUserService;
    private final CounterService counterService;

    public SendMessage processMessage(Update update) {

        String message = update.getMessage().getText();

        if (message.equals(Command.START.toString())) {
            appUserService.registerTelegramUser(update);
        }
        else if (message.startsWith(Command.CREATE_COUNTER.toString())) {
            counterService.createCounter(update);
        }

        return messages.createMessage(
                update.getMessage().getChatId(),
                "Success!"
        );
    }
}
