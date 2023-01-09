package com.bleschunov.daycounters.rest.controller;

import com.bleschunov.daycounters.rest.dto.CounterDto;
import com.bleschunov.daycounters.rest.mapper.CounterMapper;
import com.bleschunov.daycounters.rest.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/counter")
public class CounterController {
    private final CounterService counterService;
    private final CounterMapper counterMapper;

    @PostMapping
    public void createCounter(@RequestBody CounterDto counterDto) {
        counterService.createCounter(counterDto);
    }

    @GetMapping
    public List<CounterDto> getAllCountersByTelegramId(@RequestParam("telegram_id") long telegramId) {
        return counterService.getAllCountersByOwnerTelegramId(telegramId).stream().map(counterMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public long getCounterValue(@PathVariable("id") long id, @RequestParam("telegram_id") long telegramId) {
        return counterService.getCounterValue(id, telegramId);
    }

    @PutMapping("/reset/{id}")
    public void resetCounterById(@PathVariable("id") long id, @RequestParam("telegram_id") long telegramId) {
        counterService.resetCounterById(id, telegramId);
    }

    @DeleteMapping("/{id}")
    public void deleteCounterById(@PathVariable("id") long id, @RequestParam("telegram_id") long telegramId) {
        counterService.deleteCounterById(id, telegramId);
    }
}
