package com.pregunta.kirby.service;

import com.pregunta.kirby.dtos.CreateUserDTO;
import com.pregunta.kirby.exception.*;
import com.pregunta.kirby.model.Country;
import com.pregunta.kirby.model.Gender;
import com.pregunta.kirby.model.User;
import com.pregunta.kirby.repository.CountryRepository;
import com.pregunta.kirby.repository.GenderRepository;
import com.pregunta.kirby.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GenderRepository genderRepository, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.genderRepository = genderRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void validateFields(CreateUserDTO userDTO) throws EmptyFieldException {
        if(userDTO.getBirthdate().isEmpty() || userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()
                || userDTO.getName().isEmpty() || userDTO.getRepeatPassword().isEmpty() || userDTO.getUsername().isEmpty()
                || userDTO.getCountry() == null || userDTO.getGender() == null){
            throw new EmptyFieldException("¡No se permiten campos vacíos!");
        }
    }

    @Override
    public void validateThatPasswordsMatch(String password, String repeatPassword) throws DifferentPasswordsException {
        if (!password.equals(repeatPassword)) {
            throw new DifferentPasswordsException("¡Las contraseñas ingresadas no coinciden!");
        }
    }

    @Override
    public void validateThatTheEmailIsNotUsed(String email) throws ExistingUserException {
        User user = userRepository.findByEmail(email);
        if(user != null){
            throw new ExistingUserException("¡Ya existe un usuario con ese mail!");
        }
    }

    @Override
    public void validateThatTheUsernameIsNotUsed(String username) throws ExistingUserException {
        User user = userRepository.findByUsername(username);
        if(user != null){
            throw new ExistingUserException("¡Ya existe un usuario con ese username!");
        }
    }

    @Override
    public Gender validateThatGenderExist(Integer idGender) throws NonExistingGenderException {
        Gender gender = genderRepository.findById(idGender).orElse(null);
        if(gender == null){
            throw new NonExistingGenderException("¡El género ingresado no existe!");
        }
        return gender;
    }

    @Override
    public Country validateThatCountryExist(Integer idCountry) throws NonExistingCountryException {
        Country country = countryRepository.findById(idCountry).orElse(null);
        if(country == null){
            throw new NonExistingCountryException("¡El país ingresado no existe!");
        }
        return country;
    }

    @Override
    public void createUser(Gender gender, Country country, CreateUserDTO userDTO) {
        userRepository.save(new User(userDTO.getName(), userDTO.getUsername(), userDTO.getBirthdate(),
                BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt()), userDTO.getEmail(), country, gender));
    }
}