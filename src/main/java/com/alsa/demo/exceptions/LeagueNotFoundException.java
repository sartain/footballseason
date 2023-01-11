package com.alsa.demo.exceptions;

public class LeagueNotFoundException extends Exception{

    private final String message;

    public LeagueNotFoundException (String inputtedName) {
        this.message = String.format("No League Found of name: %s", inputtedName);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
