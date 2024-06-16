package com.example.shoestore.Domain.Service.Authenticate;

import com.example.shoestore.Domain.Request.LoginRequest;
import com.example.shoestore.Domain.Response.LoginResponse;

public interface AuthenticateService {
    LoginResponse login(LoginRequest loginRequest);
}
