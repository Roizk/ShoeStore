package com.example.shoestore.Presentation.Controller.Shoe;

import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Shoe.ShoeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        } catch (Exception e) {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllShoes() {
        try{
            List<Shoe> shoes = shoeService.getAll();
            if(shoes.isEmpty())
            {
                return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"No Shoes Found");
            } else
                return ResponseUtils.buildSuccessResponse(shoes, "Shoes retrieved successfully");}
        catch (Exception e) {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchShoes(@RequestParam String keyword) {
        List<Shoe> shoes = shoeService.search(keyword);
        return ResponseUtils.buildSuccessResponse(shoes, "Search completed successfully");
    }


}
