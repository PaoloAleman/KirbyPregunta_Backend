package com.pregunta.kirby.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

    private String name;

    @Column(unique=true)
    private String username;

    private String password;

    private LocalDate birthdate;

    @Column(unique=true)
    private String email;

    private String profilePhoto;

    @ManyToOne
    private Country country;

    @ManyToOne
    private Gender gender;

    public User(String name, String username, String birthdate, String password, String email, Country country, Gender gender) {
        this.name = name;
        this.username = username;
        this.birthdate = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.password = password;
        this.email = email;
        this.country = country;
        this.gender = gender;
    }

    public User() {

    }
}
