package com.salonbooking.service;

import com.salonbooking.domain.PaymentMethod;
import com.salonbooking.modal.PaymentOrder;
import com.salonbooking.payload.dto.BookingDTO;
import com.salonbooking.payload.dto.UserDTO;
import com.salonbooking.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO user,
                                    BookingDTO booking,
                                    PaymentMethod paymentMethod) throws RazorpayException, StripeException;

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO user,
                                          Long amount,
                                          Long orderId) throws RazorpayException;

    String createStripePaymentLink(UserDTO user,
                                   Long amount,
                                   Long orderId) throws StripeException;

    Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException;



}
