package com.alsa.demo.controllers;

import com.alsa.demo.entities.League;
import com.alsa.demo.entities.LeaguePosition;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LeaguePositionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private LeaguePosition leaguePositionFromResponse(MvcResult result) throws UnsupportedEncodingException, JsonProcessingException {
        String contentAsString = result.getResponse().getContentAsString();
        return objectMapper.readValue(contentAsString, LeaguePosition.class);
    }

    @Test
    void returnLeaguePositionGivenNameAndLeague() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(get("/leagueposition/Premier League/Arsenal"))
                .andExpect(status().isOk());
        LeaguePosition response = leaguePositionFromResponse(resultActions.andReturn());
        assertEquals("Arsenal", response.getTeam().getName());
    }

}
