package com.verizonmedia.userservice.conversion;

import com.verizonmedia.userservice.dto.UserDto;
import com.verizonmedia.userservice.entities.User;
import org.springframework.core.convert.converter.Converter;

public class UserDtoConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto userDto) {
        if(userDto == null) {
            return null;
        }
        return User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }
}
