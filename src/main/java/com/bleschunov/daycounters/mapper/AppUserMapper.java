package com.bleschunov.daycounters.mapper;

import com.bleschunov.daycounters.dto.AppUserDto;
import com.bleschunov.daycounters.model.AppUser;
import org.mapstruct.Mapper;

/**
 * @author Bleschunov Dmitry
 */
@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUser toEntity(AppUserDto authDto);
}
