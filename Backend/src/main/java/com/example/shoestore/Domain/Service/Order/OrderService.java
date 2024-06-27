package com.example.shoestore.Domain.Service.Order;

import com.example.shoestore.Domain.Request.OrderRequest;
import com.example.shoestore.Domain.Response.OrderResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
    OrderResponse createOrder(HttpServletRequest request, @RequestBody OrderRequest orderRequest);

}
