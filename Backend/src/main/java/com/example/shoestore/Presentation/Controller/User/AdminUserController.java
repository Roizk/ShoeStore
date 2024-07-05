package com.example.shoestore.Presentation.Controller.User;

import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAllUser(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size)
    {   try {
            Page<User> users = userService.getAllUser(page, size);
            return ResponseUtils.buildSuccessResponse(users, "Users retrieved successfully");
        } catch(Exception ex)
        {
            return  ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
