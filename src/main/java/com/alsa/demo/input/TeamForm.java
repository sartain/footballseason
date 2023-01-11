package com.alsa.demo.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TeamForm {

    @NotBlank
    @NotNull
    private String teamName;

    public TeamForm() {}

    public TeamForm(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
