package com.pregunta.kirby.service;

import com.pregunta.kirby.dtos.user.CreateUserDTO;
import com.pregunta.kirby.dtos.user.LoginUserDTO;
import com.pregunta.kirby.exception.*;
import com.pregunta.kirby.model.Country;
import com.pregunta.kirby.model.Gender;
import com.pregunta.kirby.model.User;

public interface UserService {
    void validateFieldsRegister(CreateUserDTO userDTO) throws EmptyFieldException;

    void validateThatPasswordsMatch(String password, String repeatPassword) throws DifferentPasswordsException;

    void validateThatTheEmailIsNotUsed(String email) throws ExistingUserException;

    void validateThatTheUsernameIsNotUsed(String username) throws ExistingUserException;

    Gender validateThatGenderExist(Integer idGender) throws NonExistingGenderException;

    Country validateThatCountryExist(Integer idCountry) throws NonExistingCountryException;

    void createUser(Gender gender, Country country, CreateUserDTO userDTO);

    void validateThatEmailCodeIsCorrect(String emailCode, Integer randomNumber) throws EmailCodeIncorrectException;

    User login(LoginUserDTO loginDTO) throws NonExistingUserException;

    void validateFieldsLogin(LoginUserDTO loginDTO) throws EmptyFieldException;
}
