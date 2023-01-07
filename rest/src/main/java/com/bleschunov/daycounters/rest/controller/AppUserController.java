package com.bleschunov.daycounters.rest.controller;

import com.bleschunov.daycounters.rest.dto.AppUserDto;
import com.bleschunov.daycounters.rest.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AppUserController {
    private final AppUserService appUserService;

    @PostMapping
    public void createUser(@RequestBody AppUserDto appUserDto) {
        appUserService.createUser(appUserDto);
    }
}
