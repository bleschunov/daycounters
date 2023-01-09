package com.bleschunov.daycounters.telegrambot.enums;

import lombok.RequiredArgsConstructor;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
public enum Command {
    GET_COUNTER_VALUE("/getcountervalue"),
    CREATE_COUNTER("/createcounter"),
    COUNTER_LIST("/counterlist"),
    RESET_COUNTER("/resetcounter"),
    DELETE_COUNTER("/deletecounter"),
    HELP("/help"),
    START("/start");

    private final String command;

    @Override
    public String toString() {
        return command;
    }
}
