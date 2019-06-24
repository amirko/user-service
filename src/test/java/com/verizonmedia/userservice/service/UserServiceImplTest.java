package com.verizonmedia.userservice.service;

import com.verizonmedia.userservice.dao.UserDao;
import com.verizonmedia.userservice.dto.UserDto;
import com.verizonmedia.userservice.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ConversionService conversionService;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void createUser() {
        User user = User.builder()
                .email("george@beatles.com")
                .firstName("George")
                .lastName("Harrison")
                .password("Something")
                .build();
        when(passwordEncoder.encode("Something")).thenReturn("bla");
        userService.createUser(user);
        assertEquals(user.getPassword(), "bla");
        verify(passwordEncoder).encode("Something");
        verify(userDao).save(user);
    }

    @Test
    public void deleteUser_returnsTrueWhenUserFound() {
        User user = User.builder()
                .build();
        when(userDao.getOne("george@beatles.com")).thenReturn(user);
        boolean res = userService.deleteUser("george@beatles.com");
        assertTrue(res);
        verify(userDao).delete(user);
    }

    @Test
    public void deleteUser_returnsFalseWhenUserNotFound() {
        User user = User.builder()
                .build();
        when(userDao.getOne("george@beatles.com")).thenReturn(null);
        boolean res = userService.deleteUser("george@beatles.com");
        assertFalse(res);
        verify(userDao, times(0)).delete(user);
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void getByEmail() {
        UserDto userDto = UserDto.builder()
                .email("george@beatles.com")
                .firstName("George")
                .lastName("Harrison")
                .build();
        User user = User.builder()
                .email("george@beatles.com")
                .firstName("George")
                .lastName("Harrison")
                .build();
        when(userDao.getOne("george@beatles.com")).thenReturn(user);
        when(conversionService.convert(user, UserDto.class)).thenReturn(userDto);
        UserDto actual = userService.getByEmail("george@beatles.com");
        assertEquals(actual, userDto);
        verify(userDao).getOne("george@beatles.com");
        verify(conversionService).convert(user, UserDto.class);
    }

    @Test
    public void changePassword() {
    }

    @Test
    public void login() {
    }
}