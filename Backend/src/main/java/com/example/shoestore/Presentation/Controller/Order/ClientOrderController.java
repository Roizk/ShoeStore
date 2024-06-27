package com.example.shoestore.Presentation.Controller.Order;

import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Request.OrderRequest;
import com.example.shoestore.Domain.Response.OrderResponse;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("api/client/order")
public class ClientOrderController {

    private final OrderService orderService;
    @GetMapping
    public ResponseEntity<ResponseObject> createOrder(HttpServletRequest request, @RequestBody OrderRequest orderRequest)
    {
        try {
            OrderResponse response = orderService.createOrder(request,orderRequest);
            return ResponseUtils.buildSuccessResponse(response, "Create order successfully");
        }catch(Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }
    }
}
