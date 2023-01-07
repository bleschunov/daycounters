package com.bleschunov.daycounters.telegrambot.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Bleschunov Dmitry
 */
@Getter
@Builder
public class AppUserDto {
    private long id;
    private long telegramId;
    private String email;
    private String telegramUsername;
    private String firstname;
    private String lastname;
}
