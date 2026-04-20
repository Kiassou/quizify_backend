package com.quizify.quizify.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Quizify Backend API - OK! Voir /actuator/mappings";
    }
}