package com.example.shoestore.Domain.Service.User;

import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Request.ProfileRequest;
import com.example.shoestore.Domain.Request.RegistrationRequest;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public interface UserService {
    User createUser(RegistrationRequest registrationRequest);
    Optional<User> getProfile(String userId);
    User updateProfile(String userId, ProfileRequest updatedUser);
    String getUserId(String email);
    Page<User> getAllUser(int page,int size)throws Exception;
}
