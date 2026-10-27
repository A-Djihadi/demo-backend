package com.example.demobackend;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String greet(String name) {
        if (name == null || name.isBlank()) {
            return "Bonjour, inconnu";
        }
        return "Bonjour, " + name;
    }
}