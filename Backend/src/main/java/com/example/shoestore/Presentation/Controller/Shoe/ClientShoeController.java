package com.example.shoestore.Presentation.Controller.Shoe;

import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Shoe.ShoeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/client/shoes")
@AllArgsConstructor
public class ClientShoeController {
    private final ShoeService shoeService;


    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getShoeById(@PathVariable String id) {
        try {
            Shoe shoe = shoeService.getShoeById(id);
            return ResponseUtils.buildSuccessResponse(shoe, "Shoe retrieved successfully");
        } catch (Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllShoes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Shoe> shoes = shoeService.getAllShoe(page, size);
            return ResponseUtils.buildSuccessResponse(shoes, "Shoes retrieved successfully");
        } catch (Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchShoes(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try{
            Page<Shoe> shoePage = shoeService.searchShoe(keyword, page, size);
            return ResponseUtils.buildSuccessResponse(shoePage, "Search completed successfully");
        } catch(Exception ex)
        {
            return  ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }


}
