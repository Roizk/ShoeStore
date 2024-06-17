package com.example.shoestore.Domain.Service.User;

import com.example.shoestore.Domain.Model.User.User;

public interface UserService {
    void saveUserVerificationToken(User theUser, String verificationToken);
}
