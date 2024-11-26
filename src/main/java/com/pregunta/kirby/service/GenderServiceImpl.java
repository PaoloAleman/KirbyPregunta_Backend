package com.pregunta.kirby.service;

import com.pregunta.kirby.model.Gender;
import com.pregunta.kirby.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Autowired
    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }
}
