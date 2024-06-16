package com.example.shoestore.Presentation.Controller.User;

import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class UserController {
    private UserService userService;
    @PostMapping("/profile")
    public ResponseEntity<ResponseObject> getUserProfile(@RequestHeader )
    {

    }
}
