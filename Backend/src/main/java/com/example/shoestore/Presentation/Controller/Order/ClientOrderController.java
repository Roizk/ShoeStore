package com.example.shoestore.Presentation.Controller.Order;

import com.example.shoestore.Domain.Model.Order.Order;
import com.example.shoestore.Domain.Request.OrderRequest;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/client/order")
public class ClientOrderController {

    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<ResponseObject> createOrder(HttpServletRequest request, @RequestBody OrderRequest orderRequest)
    {
        try {
            Order response = orderService.createOrder(request,orderRequest);
            return ResponseUtils.buildSuccessResponse(response, "Create order successfully");
        }catch(Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseObject> getOrder(@PathVariable String orderId)
    {
        try {
            Optional<Order> response = orderService.getOrderById(orderId);
            return ResponseUtils.buildSuccessResponse(response, "Get order successfully");
        }catch(Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseObject> getAllOrderByUserId(@PathVariable String userId) {
        try {
            List<Order> orders = orderService.getAllOrderByUserId(userId);
            return ResponseUtils.buildSuccessResponse(orders, "Orders retrieved successfully");
        } catch (Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }

//    @GetMapping
//    public ResponseEntity<ResponseObject> getAllOrders() {
//        try {
//            List<Order> orders = orderService.getAllOrder();
//            return ResponseUtils.buildSuccessResponse(orders, "All orders retrieved successfully");
//        } catch (Exception ex) {
//            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
//        }
//    }
}
