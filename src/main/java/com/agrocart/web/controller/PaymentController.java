package com.agrocart.web.controller;

import com.agrocart.web.dto.PaymentRequestDTO;
import com.agrocart.web.exception.PaymentProcessingException;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/create")
    public String create(@RequestBody PaymentRequestDTO dto) {
        try {
            int amount = dto.getAmount();
            RazorpayClient client = new RazorpayClient("rzp_test_XXXX", "SECRET");

            JSONObject options = new JSONObject();
            options.put("amount", amount * 100);
            options.put("currency", "INR");
            options.put("receipt", "order_rcptid_11");

            Order order = client.orders.create(options);
            return order.toString();

        } catch (Exception e) {
            throw new PaymentProcessingException("Failed to create Razorpay order", e);
        }
    }
}
