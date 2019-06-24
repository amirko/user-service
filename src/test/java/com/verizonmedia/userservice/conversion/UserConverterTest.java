package com.verizonmedia.userservice.conversion;

import com.verizonmedia.userservice.dto.UserDto;
import com.verizonmedia.userservice.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserConverterTest {

    private UserConverter userConverter = new UserConverter();

    @Test
    public void convert() {
        User user = User.builder()
                .email("john@beatles.com")
                .firstName("John")
                .lastName("Lennon")
                .password("imagine")
                .build();
        UserDto expected = UserDto.builder()
                .email("john@beatles.com")
                .firstName("John")
                .lastName("Lennon")
                .build();
        UserDto actual = userConverter.convert(user);
        assertEquals(actual, expected);
    }
}