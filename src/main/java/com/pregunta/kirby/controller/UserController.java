package com.pregunta.kirby.controller;

import com.pregunta.kirby.dtos.CreateUserDTO;
import com.pregunta.kirby.exception.*;
import com.pregunta.kirby.model.Country;
import com.pregunta.kirby.model.Gender;
import com.pregunta.kirby.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
@ResponseBody
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody CreateUserDTO userDTO) {
        try {
            System.out.println(userDTO.getName());
            userService.validateFields(userDTO);
            userService.validateThatPasswordsMatch(userDTO.getPassword(), userDTO.getRepeatPassword());
            userService.validateThatTheEmailIsNotUsed(userDTO.getEmail());
            userService.validateThatTheUsernameIsNotUsed(userDTO.getUsername());
            Gender gender = userService.validateThatGenderExist(userDTO.getGender());
            Country country = userService.validateThatCountryExist(userDTO.getCountry());
            userService.createUser(gender,country,userDTO);
        } catch (EmptyFieldException | DifferentPasswordsException | ExistingUserException |
                 NonExistingGenderException | NonExistingCountryException e) {
            return e.getMessage();
        }
        return "redirect:/login";
    }
}
