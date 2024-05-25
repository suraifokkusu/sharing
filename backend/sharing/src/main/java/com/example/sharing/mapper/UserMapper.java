package com.example.sharing.mapper;

import com.example.sharing.dto.UserDto;
import com.example.sharing.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Преобразование сущности User в DTO UserDto
    @Mappings({
            @Mapping(target = "userId", source = "user.userId"),
            @Mapping(target = "name", source = "user.name"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "telegramId", source = "user.telegramId"),
            @Mapping(target = "profileText", source = "user.profileText"),
            @Mapping(target = "rating", source = "user.rating"),
            @Mapping(target = "currencyBalance", source = "user.currencyBalance")
    })
    UserDto toDto(User user);

    // Преобразование DTO UserDto в сущность User
    @Mappings({
            @Mapping(target = "userId", source = "userDto.userId"),
            @Mapping(target = "name", source = "userDto.name"),
            @Mapping(target = "email", source = "userDto.email"),
            @Mapping(target = "telegramId", source = "userDto.telegramId"),
            @Mapping(target = "profileText", source = "userDto.profileText"),
            @Mapping(target = "rating", source = "userDto.rating"),
            @Mapping(target = "currencyBalance", source = "userDto.currencyBalance")
    })
    User toEntity(UserDto userDto);

    // Обновление существующей сущности User на основе данных из UserDto
    @Mappings({
            @Mapping(target = "name", source = "userDto.name"),
            @Mapping(target = "email", source = "userDto.email"),
            @Mapping(target = "telegramId", source = "userDto.telegramId"),
            @Mapping(target = "profileText", source = "userDto.profileText"),
            @Mapping(target = "rating", source = "userDto.rating"),
            @Mapping(target = "currencyBalance", source = "userDto.currencyBalance")
    })
    void updateEntity(@MappingTarget User user, UserDto userDto);
}
