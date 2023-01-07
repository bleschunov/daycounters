package com.bleschunov.daycounters.controller;

import com.bleschunov.daycounters.dto.AppUserDto;
import com.bleschunov.daycounters.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bleschunov Dmitry
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AppUserController {
    private final AppUserService appUserService;

    @PostMapping
    public void createUser(@RequestBody AppUserDto appUserDto) {
        appUserService.createUser(appUserDto);
    }
}
