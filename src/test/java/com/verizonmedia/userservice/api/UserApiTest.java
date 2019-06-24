package com.verizonmedia.userservice.api;

import com.verizonmedia.userservice.dto.ChangePasswordDto;
import com.verizonmedia.userservice.dto.LoginDto;
import com.verizonmedia.userservice.dto.UserDto;
import com.verizonmedia.userservice.entities.User;
import com.verizonmedia.userservice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserApiTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private UserApi userApi;

    @Test
    public void createUser() {
        User user = User.builder()
                .firstName("Paul")
                .lastName("McCartney")
                .email("paul@beatles.com")
                .password("Yesterday")
                .build();
        userApi.createUser(user);
        verify(userService).createUser(user);
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void getUser() {
        UserDto paul = UserDto.builder()
                .email("paul@beatles.com")
                .firstName("Paul")
                .lastName("McCartney")
                .build();
       when(userService.getByEmail("paul@beatles.com")).thenReturn(paul);
       UserDto actual = userApi.getUser("paul@beatles.com");
       verify(userService).getByEmail("paul@beatles.com");
       assertEquals(paul, actual);
    }

    @Test
    public void deleteUser() {
        when(userService.deleteUser("paul@beatles.com")).thenReturn(true);
        boolean deleted = userApi.deleteUser("paul@beatles.com");
        assertTrue(deleted);
        verify(userService).deleteUser("paul@beatles.com");
        when(userService.deleteUser("paul@beatles.com")).thenReturn(false);
        deleted = userApi.deleteUser("paul@beatles.com");
        assertFalse(deleted);
        verify(userService, times(2)).deleteUser("paul@beatles.com");
    }

    @Test
    public void login() {
        LoginDto loginDto = LoginDto.builder()
                .email("paul@beatles.com")
                .password("Yesterday")
                .build();
        when(userService.login("paul@beatles.com", "Yesterday")).thenReturn("Welcome Paul McCartney!");
        String actual = userApi.login(loginDto);
        assertEquals("Welcome Paul McCartney!", actual);
        verify(userService).login("paul@beatles.com", "Yesterday");
    }

    @Test
    public void changePassword() {
        ChangePasswordDto changePasswordDto = ChangePasswordDto.builder()
                .email("paul@beatles.com")
                .oldPassword("Yesterday")
                .newPassword("Help")
                .build();
        when(userService.changePassword("paul@beatles.com", "Yesterday", "Help")).thenReturn(true);
        boolean actual = userApi.changePassword(changePasswordDto);
        assertTrue(actual);
        verify(userService).changePassword("paul@beatles.com", "Yesterday", "Help");
        when(userService.changePassword("paul@beatles.com", "Yesterday", "Help")).thenReturn(false);
        actual = userApi.changePassword(changePasswordDto);
        assertFalse(actual);
        verify(userService, times(2)).changePassword("paul@beatles.com", "Yesterday", "Help");
    }
}