package com.pregunta.kirby.dtos.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    private String name;

    private String username;

    private String password;

    private String repeatPassword;

    private String birthdate;

    private String email;

    private Integer country;

    private Integer gender;

    private String emailCode;
}
