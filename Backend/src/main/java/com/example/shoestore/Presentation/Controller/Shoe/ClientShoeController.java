package com.example.shoestore.Presentation.Controller.Shoe;

import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Shoe.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/client/shoes")
public class ClientShoeController {
    private final ShoeService shoeService;

    @Autowired
    public ClientShoeController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getShoeById(@PathVariable String id) {
        try {
            Shoe shoe = shoeService.getShoeById(id);
            return ResponseUtils.buildSuccessResponse(shoe, "Shoe retrieved successfully");
        } catch (Exception e) {
            return ResponseUtils.buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllShoes() {
        try{
            List<Shoe> shoes = shoeService.getAll();
            if(shoes.isEmpty())
            {
                return ResponseUtils.buildErrorResponse(HttpStatus.NOT_FOUND,"No Shoes Found");
            } else
                return ResponseUtils.buildSuccessResponse(shoes, "Shoes retrieved successfully");}
        catch (Exception e) {
            return ResponseUtils.buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchShoes(@RequestParam String keyword) {
        List<Shoe> shoes = shoeService.search(keyword);
        return ResponseUtils.buildSuccessResponse(shoes, "Search completed successfully");
    }


}
