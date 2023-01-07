package com.bleschunov.daycounters.rest.mapper;

import com.bleschunov.daycounters.rest.dto.AppUserDto;
import com.bleschunov.daycounters.rest.model.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring")
public interface AppUserMapper {
    @Mapping(target = "counters", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AppUser toEntity(AppUserDto authDto);
}
