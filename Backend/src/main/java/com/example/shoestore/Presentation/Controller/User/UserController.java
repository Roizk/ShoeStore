package com.example.shoestore.Presentation.Controller.User;

import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Request.ProfileRequest;
import com.example.shoestore.Domain.Response.ProfileResponse;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    public ResponseEntity<ResponseObject> getUserProfile(@RequestParam String id)
    {
        Optional<User> userProfileOptional = userService.getProfile(id);
        if (userProfileOptional.isPresent()) {
            User userProfile = userProfileOptional.get();
            ProfileResponse response = ProfileResponse.builder()
                    .firstName(userProfile.getFirstName())
                    .lastName(userProfile.getLastName())
                    .email(userProfile.getEmail())
                    .userName(userProfile.getUserName())
                    .address(userProfile.getAddress())
                    .build();
            return ResponseUtils.buildSuccessResponse(response,"Profile retrieved successfully");}
        else{
            return ResponseUtils.buildErrorResponse(HttpStatus.NOT_FOUND,"User not Found");
        }
    }

    @PutMapping
    public ResponseEntity<ResponseObject> updateProfile(@RequestParam String id, @RequestBody ProfileRequest updatedUser)
    {
        try{
            User userProfile =userService.updateProfile(id,updatedUser);
            ProfileResponse response = ProfileResponse.builder()
                    .firstName(userProfile.getFirstName())
                    .lastName(userProfile.getLastName())
                    .email(userProfile.getEmail())
                    .userName(userProfile.getUserName())
                    .address(userProfile.getAddress())
                    .build();
            return ResponseUtils.buildSuccessResponse(response,"Profile updated");
        }
        catch(Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED,"Can not update profile");
        }
    }
}
