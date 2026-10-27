package com.example.demobackend;

import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@CrossOrigin(origins = "http://localhost:4201")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/api/greeting")
    public GreetingResponse greeting(@RequestParam(required = false) @Size(max = 50) String name) {
        return new GreetingResponse(greetingService.greet(name), false);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GreetingResponse handleConstraintViolation(jakarta.validation.ConstraintViolationException ex) {
        return new GreetingResponse("Requete invalide", false);
    }

    public record GreetingResponse(String message, boolean secretLoaded) {
    }
}