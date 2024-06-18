package com.example.shoestore.Domain.Service.User;

import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Request.RegistrationRequest;
import org.springframework.stereotype.Service;


public interface UserService {
    User createUser(RegistrationRequest registrationRequest);
}
