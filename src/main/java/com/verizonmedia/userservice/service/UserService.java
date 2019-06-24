package com.verizonmedia.userservice.service;

import com.verizonmedia.userservice.dto.UserDto;
import com.verizonmedia.userservice.entities.User;

public interface UserService {

    void createUser(User user);
    boolean deleteUser(String email);
    boolean updateUser(UserDto user);
    UserDto getByEmail(String email);
    boolean changePassword(String email, String oldPassword, String newPassword);
    String login(String email, String password);
}
