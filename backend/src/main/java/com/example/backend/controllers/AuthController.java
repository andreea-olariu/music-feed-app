package com.example.backend.controllers;

import com.example.backend.dto.LoginRequestBody;
import com.example.backend.dto.RegisterRequestBody;
import com.example.backend.exceptions.AlreadyExistentUser;
import com.example.backend.exceptions.NonexistentUser;
import com.example.backend.exceptions.WrongPassword;
import com.example.backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestBody loginDetails) throws NoSuchAlgorithmException, WrongPassword {
        try {
            authService.login(loginDetails);
            return new ResponseEntity<>(loginDetails.getEmail(), HttpStatus.OK);
        } catch (NonexistentUser e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NoSuchAlgorithmException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (WrongPassword e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestBody registerRequestBody) throws NoSuchAlgorithmException {
        try {
            authService.register(registerRequestBody);
            return new ResponseEntity<>(registerRequestBody.getEmail(), HttpStatus.OK);
        } catch (AlreadyExistentUser e) {
            return new ResponseEntity<>("User already exists", HttpStatus.OK);
        } catch (NoSuchAlgorithmException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
