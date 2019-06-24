package com.verizonmedia.userservice.service;

import com.verizonmedia.userservice.dao.UserDao;
import com.verizonmedia.userservice.dto.UserDto;
import com.verizonmedia.userservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ConversionService conversionService;

    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public boolean deleteUser(String email) {
        User user = findUser(email);
        if(user == null) {
            return false;
        }
        userDao.delete(user);
        return true;
    }

    @Override
    public boolean updateUser(UserDto user) {
        User existingUser = userDao.getOne(user.getEmail());
        if (user == null) {
            return false;
        }
        User updatedUser = conversionService.convert(user, User.class);
        updatedUser.setPassword(existingUser.getPassword());
        userDao.save(updatedUser);
        return true;
    }

    @Override
    public UserDto getByEmail(String email) {
        User user;
        try { // I would've used a ControllerAspect if I had more time
            user = userDao.getOne(email);
        }
        catch (Exception e) {
            user = null;
        }
        return conversionService.convert(user, UserDto.class);
    }

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        User user = findUser(email);
        if(user == null) {
            return false;
        }
        if(!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userDao.save(user);
        return true;
    }

    @Override
    public String login(String email, String password) {
        User user = findUser(email);
        if(user == null) {
            return loginFailed();
        }
        if(!passwordEncoder.matches(password, user.getPassword())) {
            return loginFailed();
        }
        return loginSuccess(user.getFirstName(), user.getLastName());
    }

    private User findUser(String email) {
        return userDao.getOne(email);
    }

    private String loginSuccess(String firstName, String lastName) {
        return String.format("Welcome %s %s!", firstName, lastName);
    }

    private String loginFailed() {
        return "Access denied";
    }
}
