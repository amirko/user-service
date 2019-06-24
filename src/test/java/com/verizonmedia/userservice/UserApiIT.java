package com.verizonmedia.userservice;

import com.verizonmedia.userservice.api.UserApi;
import com.verizonmedia.userservice.dto.ChangePasswordDto;
import com.verizonmedia.userservice.dto.LoginDto;
import com.verizonmedia.userservice.dto.UserDto;
import com.verizonmedia.userservice.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApiIT {

	@Autowired
	private UserApi userApi;
	@Autowired
	private ConversionService conversionService;

	@Test
    @Transactional
	public void contextLoads() {
		User user = User.builder()
				.email("a@a.com")
				.firstName("Donald")
				.lastName("Trump")
				.password("123")
				.build();
		userApi.createUser(user);
		UserDto expectedUser = conversionService.convert(user, UserDto.class);
		UserDto existingUser = userApi.getUser("a@a.com");
		assertEquals(expectedUser, existingUser);
        expectedUser.setFirstName("Bart");
		userApi.updateUser(expectedUser);
		existingUser = userApi.getUser("a@a.com");
		assertEquals(expectedUser, existingUser);
		String loginMessage = userApi.login(LoginDto.builder()
                .email("a@a.com")
                .password("456")
                .build());
		assertEquals("Access denied", loginMessage);
        loginMessage = userApi.login(LoginDto.builder()
                .email("a@a.com")
                .password("123")
                .build());
        assertEquals("Welcome Bart Trump!", loginMessage);
        boolean passwordChangeSuccess = userApi.changePassword(ChangePasswordDto.builder()
                .email("a@a.com")
                .oldPassword("124")
                .newPassword("456")
                .build());
        assertFalse(passwordChangeSuccess);
        passwordChangeSuccess = userApi.changePassword(ChangePasswordDto.builder()
                .email("a@a.com")
                .oldPassword("123")
                .newPassword("456")
                .build());
        assertTrue(passwordChangeSuccess);
        loginMessage = userApi.login(LoginDto.builder()
                .email("a@a.com")
                .password("123")
                .build());
        assertEquals("Access denied", loginMessage);
        loginMessage = userApi.login(LoginDto.builder()
                .email("a@a.com")
                .password("456")
                .build());
        assertEquals("Welcome Bart Trump!", loginMessage);
        userApi.deleteUser("a@a.com");
        existingUser = userApi.getUser("a@a.com");
        assertNull(existingUser);
    }

}
