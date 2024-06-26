package com.example.shoestore.Presentation.Controller.Authen;

import com.example.shoestore.Domain.Model.Token.VerificationToken;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Request.LoginRequest;
import com.example.shoestore.Domain.Request.RegistrationRequest;
import com.example.shoestore.Domain.Response.LoginResponse;
import com.example.shoestore.Domain.Response.RegistResponse;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Authenticate.AuthenticateService;
import com.example.shoestore.Domain.event.RegistrationCompleteEvent;
import com.example.shoestore.Presentation.Controller.User.UserController;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final UserController userController;
    private final AuthenticateService authenService;
    private final ApplicationEventPublisher publisher;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginRequest loginRequest)
    {
        LoginResponse response = authenService.login(loginRequest);
        if (response.message() != null) {
            return ResponseUtils.buildErrorResponse(HttpStatus.NOT_FOUND, response.message());
        } else {
            return ResponseUtils.buildSuccessResponse(response, "SUCCESSFULLY");
        }
    }

    public String extractToken(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        String tokenParam = request.getParameter("token");
        if (tokenParam != null && !tokenParam.isEmpty()) {
            return tokenParam;
        }
        return null;
    }
    public String extractEmail(String token)
    {
        return authenService.extractEmail(token);
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest httpServletRequest)
    {
        User user = authenService.register(registrationRequest);
        try {
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(httpServletRequest)));
        } catch (Exception ex)
        {
            throw new RuntimeException("Đã xảy ra lỗi trong quá trình gửi mail", ex);
        }
        RegistResponse response = RegistResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail()).build();
        return ResponseUtils.buildCreatedResponse(response,"Register successfully");
    }
    @GetMapping("/verifyEmail")
    public ResponseEntity<Object> verifyEmail(@RequestParam("token") String token) {
        VerificationToken theToken = authenService.getToken(token);
        String message = "";

        if (theToken == null) {
            message = "Invalid token.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponse(false, message));
        }

        if (theToken.getUser().isEnabled()) {
            message = "This account has already been verified. Please login.";
            return ResponseEntity.ok(getResponse(true, message));
        }

        String verificationResult = authenService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")) {
            message = "Email verified successfully. Now you can login.";
            return ResponseEntity.ok(getResponse(true, message));
        }

        message = "Invalid verification token.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponse(false, message));
    }

    private Object getResponse(boolean success, String message) {
        return Map.of("success", success, "message", message);
    }


    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();

    }
}
