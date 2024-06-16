package com.example.shoestore.Presentation.Controller.Shoe;

import com.example.shoestore.Domain.DTO.ShoeDTO;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Shoe.ShoeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/client/shoes")
@RequiredArgsConstructor
public class ShoeController {
    private ShoeService shoeService;
    @GetMapping("/getAll")
    public ResponseEntity<ResponseObject> getAll()
    {
        List<ShoeDTO> shoes= shoeService.getAll();
        if (shoes.isEmpty())
            return ResponseUtils.buildErrorResponse(HttpStatus.NOT_FOUND, "NOT FOUND ANY SHOES");
        else
            return ResponseUtils.buildSuccessResponse(shoes, "SUCCESSFULLY");
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseObject> search(@RequestParam(name = "keyword", required = false) String keyword)
    {
        List<Shoe> shoes = shoeService.search(keyword);
        if (!shoes.isEmpty()) {
            return ResponseUtils.buildSuccessResponse(shoes, "SUCCESSFULLY FOUND BOOK : " + keyword);
        } else {
            return ResponseUtils.buildErrorResponse(HttpStatus.NOT_FOUND, "NOT FOUND BOOK: " + keyword);
        }
    }

}
