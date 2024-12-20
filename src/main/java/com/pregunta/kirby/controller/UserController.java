package com.pregunta.kirby.controller;

import com.pregunta.kirby.dtos.user.LoginUserDTO;
import com.pregunta.kirby.dtos.user.CreateUserDTO;
import com.pregunta.kirby.dtos.user.UserDTO;
import com.pregunta.kirby.exception.*;
import com.pregunta.kirby.model.Country;
import com.pregunta.kirby.model.Gender;
import com.pregunta.kirby.model.User;
import com.pregunta.kirby.service.EmailService;
import com.pregunta.kirby.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    final private UserService userService;
    final private EmailService emailService;
    final private HttpSession session;

    @Autowired
    public UserController(UserService userService, EmailService emailService, HttpSession session) {
        this.userService = userService;
        this.emailService = emailService;
        this.session = session;
    }

    @PostMapping("/createUser")
    @ResponseBody
    public String createUser(@RequestBody CreateUserDTO userDTO) {
        try {
            userService.validateFieldsRegister(userDTO);
            userService.validateThatPasswordsMatch(userDTO.getPassword(), userDTO.getRepeatPassword());
            userService.validateThatTheEmailIsNotUsed(userDTO.getEmail());
            userService.validateThatTheUsernameIsNotUsed(userDTO.getUsername());
            userService.validateThatEmailCodeIsCorrect(userDTO.getEmailCode(), (Integer) session.getAttribute("randomNumber"));
            Gender gender = userService.validateThatGenderExist(userDTO.getGender());
            Country country = userService.validateThatCountryExist(userDTO.getCountry());
            userService.createUser(gender,country,userDTO);
        } catch (EmptyFieldException | DifferentPasswordsException | ExistingUserException |
                 NonExistingGenderException | NonExistingCountryException | EmailCodeIncorrectException e) {
            return e.getMessage();
        }
        return "¡Usuario creado correctamente!";
    }

    @PostMapping("/sendMail")
    @ResponseBody
    public String sendMail(@RequestParam("email") String email) {
        try {
            Random random = new Random();
            session.setAttribute("randomNumber", random.nextInt(1000000));
            userService.validateThatTheEmailIsNotUsed(email);
            emailService.sendEmail(email, (Integer) session.getAttribute("randomNumber"));
        } catch (MessagingException e) {
            return "¡Ocurrió un error al enviar el mail!";
        } catch (ExistingUserException e) {
            return e.getMessage();
        }
        return null;
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginUserDTO loginDTO) {
        try {
            userService.validateFieldsLogin(loginDTO);
            User user = userService.login(loginDTO);
            session.setAttribute("user", user);
        } catch (NonExistingUserException | EmptyFieldException e) {
            return e.getMessage();
        }
        return null;
    }

    @GetMapping("/session")
    @ResponseBody
    public UserDTO session() {
        User user = (User) session.getAttribute("user");
        return new UserDTO(user.getName(),user.getBirthdate(),user.getEmail(),user.getGender(),
                                    user.getCountry(),user.getProfilePhoto(),user.getUsername());
    }
}
