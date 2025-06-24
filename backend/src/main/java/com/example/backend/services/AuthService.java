package com.example.backend.services;

import com.example.backend.dto.LoginRequestBody;
import com.example.backend.dto.RegisterRequestBody;
import com.example.backend.exceptions.AlreadyExistentUser;
import com.example.backend.exceptions.NonexistentUser;
import com.example.backend.exceptions.WrongPassword;
import com.example.backend.models.User;
import com.example.backend.utils.Hasher;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AuthService {

    private final UsersService usersService;

    public AuthService(UsersService usersService) {
        this.usersService = usersService;
    }

    public void login(LoginRequestBody loginDetails) throws NonexistentUser, WrongPassword, NoSuchAlgorithmException {
        try {
            User existentUser = usersService.findByEmail(loginDetails.getEmail());
            String hashedPassword = Hasher.sha256(loginDetails.getPassword());

            if(!hashedPassword.equals(existentUser.getPassword())) {
                throw new WrongPassword();
            }
        } catch (NonexistentUser e) {
            throw new NonexistentUser();
        }
    }

    public void register(RegisterRequestBody registerRequestBody) throws AlreadyExistentUser, NoSuchAlgorithmException {
        try {
            usersService.findByEmail(registerRequestBody.getEmail());
            throw new AlreadyExistentUser();
        } catch (NonexistentUser e) {
            usersService.saveUser(registerRequestBody);
        }
    }
}
