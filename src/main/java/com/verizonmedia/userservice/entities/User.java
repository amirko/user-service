package com.verizonmedia.userservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;

}
