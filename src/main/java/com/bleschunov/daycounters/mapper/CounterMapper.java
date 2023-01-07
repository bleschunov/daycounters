package com.bleschunov.daycounters.mapper;

import com.bleschunov.daycounters.dto.CounterDto;
import com.bleschunov.daycounters.model.Counter;
import org.mapstruct.Mapper;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring")
public interface CounterMapper {
    Counter toEntity(CounterDto counterDto);
}
