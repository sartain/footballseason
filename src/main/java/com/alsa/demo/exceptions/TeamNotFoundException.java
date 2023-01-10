package com.alsa.demo.exceptions;

public class TeamNotFoundException extends Exception {

    private final String message;

    public TeamNotFoundException (String inputtedName) {
        this.message = String.format("No Team Found of name: %s", inputtedName);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
