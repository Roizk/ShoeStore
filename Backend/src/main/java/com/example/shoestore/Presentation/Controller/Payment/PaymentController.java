package com.example.shoestore.Presentation.Controller.Payment;

import com.example.shoestore.Domain.Model.Order.Order;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Payment.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("api/client/payment")
public class PaymentController {
    private final PaymentService paymentService;
    @GetMapping
    public ResponseEntity<ResponseObject> createOrder(HttpServletRequest req
            , @RequestParam long amount
            , @RequestParam String bankCode
            , @RequestParam String orderInfo) {
        try {
            String paymentUrl = paymentService.createPayment(req, amount, bankCode, orderInfo);
            return ResponseUtils.buildSuccessResponse(paymentUrl, "Created successfully payment");
        } catch (Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.EXPECTATION_FAILED,"Create payment failed");
        }
    }
    @GetMapping("/success")
    public ResponseEntity<ResponseObject> paymentSuccess(@RequestParam long vnp_OrderInfo
            , @RequestParam String vnp_PayDate
            , @RequestParam long vnp_Amount
            , @RequestParam String vnp_BankCode
            , @RequestParam String vnp_CardType
    ) {
        try {

            Order response = paymentService.paymentSuccess(vnp_OrderInfo, vnp_PayDate, vnp_Amount, vnp_BankCode, vnp_CardType);

            return ResponseUtils.buildSuccessResponse(response, "Payment successfully");
        } catch (Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
