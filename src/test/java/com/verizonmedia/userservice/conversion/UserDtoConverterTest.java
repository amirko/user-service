package com.verizonmedia.userservice.conversion;

import com.verizonmedia.userservice.dto.UserDto;
import com.verizonmedia.userservice.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDtoConverterTest {

    private UserDtoConverter userDtoConverter = new UserDtoConverter();

    @Test
    public void convert() {
        UserDto userDto = UserDto.builder()
                .email("john@beatles.com")
                .firstName("John")
                .lastName("Lennon")
                .build();
        User expected = User.builder()
                .email("john@beatles.com")
                .firstName("John")
                .lastName("Lennon")
                .build();
        User actual = userDtoConverter.convert(userDto);
        assertEquals(actual, expected);
    }
}