package com.salonbooking.controller;

import com.salonbooking.domain.PaymentMethod;
import com.salonbooking.modal.PaymentOrder;
import com.salonbooking.payload.dto.BookingDTO;
import com.salonbooking.payload.dto.UserDTO;
import com.salonbooking.payload.response.PaymentLinkResponse;
import com.salonbooking.service.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDTO booking,
            @RequestParam PaymentMethod paymentMethod
            ) throws StripeException, RazorpayException {

        UserDTO user=new UserDTO();
        user.setFullName("Golu");
        user.setEmail("golu@gmail.com");
        user.setId(1L);

        PaymentLinkResponse res=paymentService.createOrder(user,booking,paymentMethod);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(
            @PathVariable Long paymentOrderId
    ) throws Exception {
        PaymentOrder res=paymentService.getPaymentOrderById(paymentOrderId);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId
    ) throws Exception {

        PaymentOrder paymentOrder=paymentService.getPaymentOrderByPaymentId(paymentLinkId);
        Boolean res=paymentService.proceedPayment(paymentOrder,paymentId,paymentLinkId);
        return ResponseEntity.ok(res);
    }



}
