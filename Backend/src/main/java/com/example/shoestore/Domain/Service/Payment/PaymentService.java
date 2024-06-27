package com.example.shoestore.Domain.Service.Payment;

import com.example.shoestore.Domain.Model.Order.Order;
import com.example.shoestore.Domain.Response.OrderResponse;

import javax.servlet.http.HttpServletRequest;

public interface PaymentService {
    String createPayment(HttpServletRequest req, Long amount, String bankCode, long id) throws Exception;
    Order paymentSuccess(long vnp_OrderInfo, String vnp_PayDate
            , long vnp_Amount, String vnp_BankCode, String vnp_CardType);
}
