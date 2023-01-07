package com.bleschunov.daycounters.telegrambot.enums;

import lombok.RequiredArgsConstructor;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
public enum Command {
    GET_COUNTER_VALUE("/getcountervalue"),
    CREATE_COUNTER("/createcounter"),
    START("/start");

    private final String command;

    @Override
    public String toString() {
        return command;
    }
}
