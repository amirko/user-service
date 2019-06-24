package com.verizonmedia.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

    private String email;
    private String oldPassword;
    private String newPassword;
}
