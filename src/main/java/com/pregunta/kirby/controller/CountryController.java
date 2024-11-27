package com.pregunta.kirby.controller;

import com.pregunta.kirby.model.Country;
import com.pregunta.kirby.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@ResponseBody
public class CountryController {

    final private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<Country> countries() {
        return countryService.getAllCountries();
    }
}
