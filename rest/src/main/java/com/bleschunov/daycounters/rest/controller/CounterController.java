package com.bleschunov.daycounters.rest.controller;

import com.bleschunov.daycounters.rest.dto.CounterDto;
import com.bleschunov.daycounters.rest.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bleschunov Dmitry
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/counter")
public class CounterController {
    private final CounterService counterService;

    @PostMapping
    public void createCounter(@RequestBody CounterDto counterDto) {
        counterService.createCounter(counterDto);
    }

    @GetMapping("/{title}")
    public long getCounterValue(@PathVariable String title) {
        return counterService.getCounterValue(title);
    }
}
