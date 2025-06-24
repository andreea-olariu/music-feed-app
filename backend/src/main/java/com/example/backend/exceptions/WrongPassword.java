package com.example.backend.exceptions;

public class WrongPassword extends Exception {

    public WrongPassword() {
        super("Wrong password");
    }
}
