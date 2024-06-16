package com.example.shoestore.Domain.Service.Authenticate;

import com.example.shoestore.Domain.Request.LoginRequest;
import com.example.shoestore.Domain.Response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImp implements AuthenticateService{

    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(AuthenticateServiceImp.class);
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            LoginRequest.email(),
                            LoginRequest.password()
                    )
            );
            User user = userrepos.findByEmail(request.email())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng cho email: " + request.email()));

            var user1 = new UserAuthDetails(user);
            logger.info("JWT: " + jwtService.generateToken(user1));

            String role = user.getRole();
            String name = user.getLastName();
            String username = user.getEmail();
            String jwtToken = jwtService.generateToken(user1);
            logger.info("Role: " + user1.getAuthorities());
            System.out.println("ROLE: " + user1.getAuthorities());
            session.setAttribute("USER",user);
            return AuthenticationResponse.builder().username(username).role(role).name(name).token(jwtToken).build();
        } catch (
                BadCredentialsException ex) {
            return AuthenticationResponse.builder().message("Wrong username or password").build();
        } catch (Exception ex) {
            return AuthenticationResponse.builder().message("Account is disable").build();
        }
    }
}
