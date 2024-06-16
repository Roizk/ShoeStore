package com.example.shoestore.Presentation.Controller.Authen;

import com.example.shoestore.Domain.Request.LoginRequest;
import com.example.shoestore.Domain.Response.LoginResponse;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Authenticate.AuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticateService authenService;

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginRequest loginRequest)
    {
        LoginResponse response = authenService.login(LoginRequest);
        if (response.message() != null) {
            return ResponseUtils.buildErrorResponse(HttpStatus.NOT_FOUND, response.message());
        } else {
            return ResponseUtils.buildSuccessResponse(response, "SUCCESSFULLY");
        }
    }
}
