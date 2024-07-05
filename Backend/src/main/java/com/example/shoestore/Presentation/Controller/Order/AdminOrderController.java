package com.example.shoestore.Presentation.Controller.Order;

import com.example.shoestore.Domain.Model.Order.Order;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/admin/order")
public class AdminOrderController {
    private final OrderService orderService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Order> orders = orderService.getAllOrder(page,size);
            return ResponseUtils.buildSuccessResponse(orders, "All orders retrieved successfully");
        } catch (Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }
}
