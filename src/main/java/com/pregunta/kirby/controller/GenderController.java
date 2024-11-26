package com.pregunta.kirby.controller;

import com.pregunta.kirby.model.Gender;
import com.pregunta.kirby.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
public class GenderController {

    final private GenderService genderService;

    @Autowired
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping("/genders")
    @ResponseBody
    public List<Gender> genders() {
        return genderService.getAllGenders();
    }
}