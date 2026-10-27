package com.example.demobackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)
@Import(GreetingService.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void renvoieLeMessageAvecLeNom() throws Exception {
        mockMvc.perform(get("/api/greeting").param("name", "Ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Bonjour, Ana"))
                .andExpect(jsonPath("$.secretLoaded").value(false));
    }

    @Test
    void renvoieInconnuSansNom() throws Exception {
        mockMvc.perform(get("/api/greeting"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Bonjour, inconnu"));
    }

    @Test
    void refuseUnNomTropLong() throws Exception {
        mockMvc.perform(get("/api/greeting").param("name", "a".repeat(51)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void autoriseLOrigineDuFront() throws Exception {
        mockMvc.perform(get("/api/greeting").param("name", "Ana")
                        .header("Origin", "http://localhost:4201"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:4201"));
    }
}