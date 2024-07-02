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

@RestController
@AllArgsConstructor
@RequestMapping("api/admin/shoes")
public class AdminShoeController {

    private final ShoeService shoeService;

    @PostMapping
    public ResponseEntity<ResponseObject> createShoe(@RequestBody Shoe shoe) {
        Shoe createdShoe = shoeService.createShoe(shoe);
        return ResponseUtils.buildSuccessResponse(createdShoe, HttpStatus.CREATED.getReasonPhrase());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateShoe(@PathVariable String id, @RequestBody Shoe shoeDetails) {
        try {
            Shoe updatedShoe = shoeService.updateShoe(id, shoeDetails);
            return ResponseUtils.buildSuccessResponse(updatedShoe, "Shoe updated successfully");
        } catch (RuntimeException e) {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteShoe(@PathVariable String id) {
        try {
            shoeService.deleteShoe(id);
            return ResponseUtils.buildSuccessResponse(null, "Shoe deleted successfully");
        } catch (RuntimeException e) {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
