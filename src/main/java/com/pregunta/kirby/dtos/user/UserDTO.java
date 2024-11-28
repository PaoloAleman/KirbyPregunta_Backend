package com.pregunta.kirby.dtos.user;

import com.pregunta.kirby.model.Country;
import com.pregunta.kirby.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {

    private String name;

    private String username;

    private LocalDate birthdate;

    private String email;

    private String profilePhoto;

    private Country country;

    private Gender gender;

    public UserDTO(String name, LocalDate birthdate, String email, Gender gender, Country country, String profilePhoto, String username) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.profilePhoto = profilePhoto;
        this.username = username;
    }
}
