package com.example.shoestore.Domain.Service.Authenticate;

import com.example.shoestore.Domain.Model.Token.VerificationToken;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Model.User.UserAuthDetails;
import com.example.shoestore.Domain.Request.LoginRequest;
import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Request.RegistrationRequest;
import com.example.shoestore.Domain.Response.LoginResponse;
import com.example.shoestore.Domain.Response.RegistResponse;
import com.example.shoestore.Domain.Security.JWTAuth.JwtService;
import com.example.shoestore.Domain.Service.User.UserService;
import com.example.shoestore.Persistence.Repository.UserRepository;
import com.example.shoestore.Persistence.Repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImp implements AuthenticateService{

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final VerificationTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(AuthenticateServiceImp.class);
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()
                    )
            );
            User user = userRepository.findByEmail(loginRequest.email())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng cho email: " + loginRequest.email()));

            var user1 = new UserAuthDetails(user);
            logger.info("JWT: " + jwtService.generateToken(user1));

            String role = user.getRole();
            String name = user.getLastName();
            String username = user.getEmail();
            String jwtToken = jwtService.generateToken(user1);
            logger.info("Role: " + user1.getAuthorities());
            System.out.println("ROLE: " + user1.getAuthorities());
            return LoginResponse.builder().username(username).role(role).name(name).token(jwtToken).build();
        } catch (
                BadCredentialsException ex) {
            return LoginResponse.builder().message("Wrong username or password").build();
        } catch (Exception ex) {
            return LoginResponse.builder().message("Account is disable").build();
        }
    }

    @Override
    public String ValidateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if (token == null) {
            return "Invalid verification token";
        }

        // Fetch the User document using its ID stored in VerificationToken
        User user = userRepository.findById(Long.valueOf(token.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            tokenRepository.delete(token);
            return "Token already expired";
        }

        user.setEnabled(true);
        userRepository.save(user);
        return "Valid";
    }


    @Override
    public VerificationToken getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public User register(RegistrationRequest registrationRequest) {
        try {
            User newUser = userService.createUser(registrationRequest);

            var userAuthDetails = new UserAuthDetails(newUser);
            var jwtToken = jwtService.generateToken(userAuthDetails);
            logger.info("JWT:" + jwtToken);

            return newUser;
        } catch (Exception ex) {
            throw new RuntimeException("Đã xảy ra lỗi trong quá trình đăng ký", ex);
        }
    }
    @Override
    public void saveUserVerificationToken(User theUser, String token) {
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);
    }
}
