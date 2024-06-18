package com.example.shoestore.Presentation.Controller.Authen;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest httpServletRequest)
    {
        User user = authenService.register(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(httpServletRequest)));
        RegistResponse response = RegistResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail()).build();
        return ResponseUtils.buildCreatedResponse(response,"Register successfully");
    }
    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();

    }
}
