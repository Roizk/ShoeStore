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
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        updateUserFields(newUser, registrationRequest);
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
        String[] ignoreProperties = getNullPropertyNames(updatedUser);
        BeanUtils.copyProperties(updatedUser, user, ignoreProperties);
        return userRepository.save(user);
    }

    @Override
    public String getUserId(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        try{
            User existingUser=user.get();
            return existingUser.getId();
        } catch (Exception ex)
        {
            return "No user Found";
        }
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
    private void updateUserFields(User user, RegistrationRequest registrationRequest) {
        user.setFirstName(registrationRequest.firstName());
        user.setLastName(registrationRequest.lastName());
        user.setEmail(registrationRequest.email());
        user.setPassword(registrationRequest.password());
        user.setAddress(registrationRequest.address());
        user.setRole("USER");
    }
}
