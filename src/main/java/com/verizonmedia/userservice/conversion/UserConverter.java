package com.verizonmedia.userservice.conversion;

import com.verizonmedia.userservice.dto.UserDto;
import com.verizonmedia.userservice.entities.User;
import org.springframework.core.convert.converter.Converter;

public class UserConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        if(user == null) {
            return null;
        }
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
