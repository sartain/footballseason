package com.alsa.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LeaguePositionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnLeaguePositionGivenNameAndLeague() throws Exception {
        this.mockMvc.perform(get("/leagueposition")
                .param("league", "Premier League")
                .param("team", "Arsenal"))
                        .andExpect(status().isOk());
    }

}
