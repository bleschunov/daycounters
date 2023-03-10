package com.bleschunov.daycounters.rest.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Bleschunov Dmitry
 */
@Builder
@Getter
public class CounterDto {
    private long id;
    private long ownerTelegramId;
    private String title;
    private int maxValue;
}
