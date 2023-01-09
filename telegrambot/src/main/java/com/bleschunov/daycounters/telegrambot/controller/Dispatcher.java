package com.bleschunov.daycounters.telegrambot.controller;

import com.bleschunov.daycounters.telegrambot.dto.CounterDto;
import com.bleschunov.daycounters.telegrambot.enums.Command;
import com.bleschunov.daycounters.telegrambot.exception.BusinessException;
import com.bleschunov.daycounters.telegrambot.service.AppUserService;
import com.bleschunov.daycounters.telegrambot.service.CounterService;
import com.bleschunov.daycounters.telegrambot.service.TextMessageService;
import com.bleschunov.daycounters.telegrambot.util.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class Dispatcher {
    private final Messages messages;
    private final AppUserService appUserService;
    private final CounterService counterService;
    private final TextMessageService textMessageService;

    public SendMessage processMessage(Update update) {

        String message = update.getMessage().getText();
        String command = messages.getCommand(message);

        try {
            if (command.equals(Command.START.toString())) {
                appUserService.registerTelegramUser(update);
                return textMessageService.getStartingText(update);
            }
            if (command.equals(Command.HELP.toString())) {
                return textMessageService.getHelpText(update);
            }
            else if (command.equals(Command.CREATE_COUNTER.toString())) {
                counterService.createCounter(update);
                return textMessageService.getCounterSuccessfullyCreatedText(update);
            }
            else if (command.equals(Command.COUNTER_LIST.toString())) {
                List<CounterDto> counterDtos = counterService.getAllAvailableCounters(update);
                return textMessageService.getAllAvailableCountersText(update, counterDtos);
            }
            else if (command.equals(Command.GET_COUNTER_VALUE.toString())) {
                long counterValue = counterService.getCounterValue(update);
                return textMessageService.getCounterValueText(update, counterValue);
            }
            else if (command.equals(Command.RESET_COUNTER.toString())) {
                counterService.resetCounter(update);
                return textMessageService.getCounterResetText(update, messages.getCommandParam(message));
            }
            else if (command.equals(Command.DELETE_COUNTER.toString())) {
                counterService.deleteCounter(update);
                return textMessageService.getCounterDeletedText(update, messages.getCommandParam(message));
            }

            throw new BusinessException("This command is not supported.");
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return textMessageService.getExceptionMessage(update, e);
        }
    }
}
