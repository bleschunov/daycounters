package com.bleschunov.daycounters.rest.mapper;

import com.bleschunov.daycounters.rest.dto.CounterDto;
import com.bleschunov.daycounters.rest.model.AppUser;
import com.bleschunov.daycounters.rest.model.Counter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring")
public interface CounterMapper {
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Counter toEntity(CounterDto counterDto);

    @Mapping(source = "owner", target = "ownerTelegramId", qualifiedByName = "ownerTelegramId")
    CounterDto toDto(Counter counter);

    @Named("ownerTelegramId")
    default long AppUserToId(AppUser appUser) {
        return appUser.getTelegramId();
    }
}
