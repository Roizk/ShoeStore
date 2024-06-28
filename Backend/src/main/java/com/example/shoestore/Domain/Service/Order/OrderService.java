package com.example.shoestore.Domain.Service.Order;

import com.example.shoestore.Domain.Model.Order.Order;
import com.example.shoestore.Domain.Request.OrderRequest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(HttpServletRequest request,OrderRequest orderRequest)throws Exception;
    Optional<Order> getOrderById(String orderId)throws Exception;
    List<Order> getAllOrderByUserId(String userId)throws Exception;
    List<Order> getAllOrder()throws Exception;
}
