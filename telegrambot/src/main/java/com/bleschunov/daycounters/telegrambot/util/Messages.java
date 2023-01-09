package com.bleschunov.daycounters.telegrambot.util;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Bleschunov Dmitry
 */
@Component
public class Messages {
    public SendMessage createSelfMessage(Update update, String message) {
        return createMessage(update.getMessage().getChatId(), message);
    }

    public SendMessage createMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        return sendMessage;
    }

    public String getCommand(String messageText) {
        try {
            return messageText.substring(0, messageText.indexOf(' ')).trim();
        } catch (IndexOutOfBoundsException e) {
            return messageText;
        }

    }

    public String getCommandParam(String messageText) {
        try {
            return messageText.substring(messageText.indexOf(' ')).trim();
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }
}
