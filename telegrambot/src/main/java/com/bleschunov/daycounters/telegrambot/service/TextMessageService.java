package com.bleschunov.daycounters.telegrambot.service;

import com.bleschunov.daycounters.telegrambot.dto.CounterDto;
import com.bleschunov.daycounters.telegrambot.util.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class TextMessageService {

    private final Messages messages;

    public SendMessage getStartingText(Update update) {
        String messageText = "Welcome to Daycounters!\n" +
                "To see all available commands print /help";

        return messages.createSelfMessage(update, messageText);
    }

    public SendMessage getHelpText(Update update) {
        String messageText = "List of available commands:\n" +
                "/createcounter <title> - creates new counter\n" +
                "/counterlist - shows all available counters\n" +
                "/getcountervalue <counter_id> - shows counter value in seconds\n" +
                "/resetcounter <counter_id> - resets counter\n" +
                "/deletecounter <counter_id> - deletes counter";

        return messages.createSelfMessage(update, messageText);
    }

    public SendMessage getCounterSuccessfullyCreatedText(Update update) {
        String counterTitle = messages.getCommandParam(update.getMessage().getText());
        return messages.createSelfMessage(update, "You " + counterTitle + " from now!");
    }

    public SendMessage getAllAvailableCountersText(Update update, List<CounterDto> counterDtos) {
        StringBuilder stringBuilder = new StringBuilder("Available counters:\n");
        counterDtos.forEach(counterDto ->
                stringBuilder
                    .append(counterDto.getTitle())
                    .append(", id: ")
                    .append(counterDto.getId())
                    .append("\n")
        );
        return messages.createSelfMessage(update, stringBuilder.toString());
    }

    public SendMessage getCounterResetText(Update update, String counterId) {
        return messages.createSelfMessage(update, "Counter with id " + counterId + " was reset.");
    }

    public SendMessage getCounterDeletedText(Update update, String counterId) {
        return messages.createSelfMessage(update, "Counter with id " + counterId + " was deleted.");
    }

    public SendMessage getCounterValueText(Update update, long counterValue) {
        return messages.createSelfMessage(update, "Its current value is " + counterValue + " seconds.");
    }

    public SendMessage getExceptionMessage(Update update, Exception e) {
        return messages.createSelfMessage(update, e.getMessage());
    }
}
