package com.example.shoestore.Domain.Service.Authenticate;

import com.example.shoestore.Domain.Model.Token.VerificationToken;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Request.LoginRequest;
import com.example.shoestore.Domain.Request.RegistrationRequest;
import com.example.shoestore.Domain.Response.LoginResponse;
import com.example.shoestore.Domain.Response.RegistResponse;
import org.springframework.stereotype.Service;


public interface AuthenticateService {
    LoginResponse login(LoginRequest loginRequest);
    String ValidateToken (String theToken);
    VerificationToken getToken(String token);
    User register(RegistrationRequest registrationRequest);
}
