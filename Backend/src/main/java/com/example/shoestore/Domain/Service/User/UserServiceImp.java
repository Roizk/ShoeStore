package com.example.shoestore.Domain.Service.User;

import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Token.VerificationToken;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Model.User.UserAuthDetails;
import com.example.shoestore.Domain.Request.RegistrationRequest;
import com.example.shoestore.Domain.Service.Cart.CartService;
import com.example.shoestore.Persistence.Repository.CartRepository;
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
    private final CartRepository cartRepository;
    private CartService cartService;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        Optional<User> existingUser = userRepository.findByEmail(registrationRequest.email());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with email " + registrationRequest.email() + " already exists");
        }
        User newUser = new User();
        setUserProperties(newUser, registrationRequest);
        cartService.createCart(newUser);

        return userRepository.save(newUser);
    }
    private void setUserProperties(User user, RegistrationRequest registrationRequest) {
        user.setFirstName(registrationRequest.firstName());
        user.setLastName(registrationRequest.lastName());
        user.setEmail(registrationRequest.email());
        user.setPassword(registrationRequest.password());
        user.setAddress(registrationRequest.address());
        user.setUsername(registrationRequest.userName());
        user.setRole("USER");
    }
}
