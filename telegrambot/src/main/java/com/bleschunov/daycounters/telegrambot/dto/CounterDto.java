package com.bleschunov.daycounters.telegrambot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Bleschunov Dmitry
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CounterDto {
    private long id;
    private long ownerTelegramId;
    private String title;
    private int maxValue;
}
