package com.verizonmedia.userservice.api;

import com.verizonmedia.userservice.dto.ChangePasswordDto;
import com.verizonmedia.userservice.dto.LoginDto;
import com.verizonmedia.userservice.dto.UserDto;
import com.verizonmedia.userservice.entities.User;
import com.verizonmedia.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping
    public boolean updateUser(@RequestBody UserDto user) {
        return userService.updateUser(user);
    }

    @GetMapping("/{email}")
    public UserDto getUser(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    @DeleteMapping("/{email}")
    public boolean deleteUser(@PathVariable String email) {
        return userService.deleteUser(email);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto.getEmail(), loginDto.getPassword());
    }

    @PutMapping("/changePassword")
    public boolean changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return userService.changePassword(changePasswordDto.getEmail(), changePasswordDto.getOldPassword(), changePasswordDto.getNewPassword());
    }
}
