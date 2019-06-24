package com.verizonmedia.userservice.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {

    private String email;
    private String firstName;
    private String lastName;
}
