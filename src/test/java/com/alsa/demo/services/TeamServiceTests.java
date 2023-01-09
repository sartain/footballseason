package com.alsa.demo.services;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class TeamServiceTests {

    @Autowired
    private TeamService service;

    @Test
    void testGetTeamNameFromId() {
        String expectedTeamName = "Everton";
        String actualName = service.getTeamFromId(1).getTeamName();
    }

}
