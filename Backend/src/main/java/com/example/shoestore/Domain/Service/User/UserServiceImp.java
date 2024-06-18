package com.example.shoestore.Domain.Service.User;

import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Token.VerificationToken;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Model.User.UserAuthDetails;
import com.example.shoestore.Domain.Request.RegistrationRequest;
import com.example.shoestore.Persistence.Repository.UserRepository;
import com.example.shoestore.Persistence.Repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        Optional<User> existingUser = userRepository.findByEmail(registrationRequest.email());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with email " + registrationRequest.email() + " already exists");
        }
        User newUser = new User();
        newUser.setFirstName(registrationRequest.firstName());
        newUser.setLastName(registrationRequest.lastName());
        newUser.setEmail(registrationRequest.email());
        newUser.setPassword(registrationRequest.password());
        newUser.setRole("USER");

        Cart cart = new Cart();
        newUser.setCart(cart);

        return userRepository.save(newUser);
    }
}
