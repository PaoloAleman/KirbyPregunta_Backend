package com.pregunta.kirby.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

    private String name;

    @Column(unique=true)
    private String username;

    private String password;

    private String birthday;

    @Column(unique=true)
    private String email;

    private String profilePhoto;

    @ManyToOne
    private City country;

    @ManyToOne
    private Gender gender;

}
