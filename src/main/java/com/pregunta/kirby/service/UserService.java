package com.pregunta.kirby.service;

import com.pregunta.kirby.dtos.user.CreateUserDTO;
import com.pregunta.kirby.exception.*;
import com.pregunta.kirby.model.Country;
import com.pregunta.kirby.model.Gender;

public interface UserService {
    void validateFields(CreateUserDTO userDTO) throws EmptyFieldException;

    void validateThatPasswordsMatch(String password, String repeatPassword) throws DifferentPasswordsException;

    void validateThatTheEmailIsNotUsed(String email) throws ExistingUserException;

    void validateThatTheUsernameIsNotUsed(String username) throws ExistingUserException;

    Gender validateThatGenderExist(Integer idGender) throws NonExistingGenderException;

    Country validateThatCountryExist(Integer idCountry) throws NonExistingCountryException;

    void createUser(Gender gender, Country country, CreateUserDTO userDTO);

    void validateThatEmailCodeIsCorrect(String emailCode, Integer randomNumber) throws EmailCodeIncorrectException;
}
