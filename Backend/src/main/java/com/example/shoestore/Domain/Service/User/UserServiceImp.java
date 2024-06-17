package com.example.shoestore.Domain.Service.User;

import com.example.shoestore.Domain.Model.Token.VerificationToken;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Persistence.Repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final VerificationTokenRepository tokenRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
    @Override
    public void saveUserVerificationToken(User theUser, String token) {

    }
}
