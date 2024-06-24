package com.example.shoestore.Domain.Service.User;

import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Request.ProfileRequest;
import com.example.shoestore.Domain.Request.RegistrationRequest;
import com.example.shoestore.Domain.Security.JWTAuth.JwtService;
import com.example.shoestore.Domain.Service.Cart.CartService;
import com.example.shoestore.Persistence.Repository.CartRepository;
import com.example.shoestore.Persistence.Repository.UserRepository;
import com.example.shoestore.Persistence.Repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final JwtService jwtService;
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

    @Override
    public Optional<User> getProfile(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    @Override
    public User updateProfile(String userId, ProfileRequest updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BeanUtils.copyProperties(updatedUser, user/*,getNullPropertyNames(updatedUser)*/);
        return userRepository.save(user);
    }

//    private String[] getNullPropertyNames(Object source) {
//        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
//        return Stream.of(wrappedSource.getPropertyDescriptors())
//                .map(FeatureDescriptor::getName)
//                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
//                .toArray(String[]::new);
//    }
    private void setUserProperties(User user, RegistrationRequest registrationRequest) {
        user.setFirstName(registrationRequest.firstName());
        user.setLastName(registrationRequest.lastName());
        user.setEmail(registrationRequest.email());
        user.setPassword(registrationRequest.password());
        user.setAddress(registrationRequest.address());
        user.setUserName(registrationRequest.userName());
        user.setRole("USER");
    }
}
