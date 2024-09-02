package org.shippingbros.ecommerce.payment;

import org.shippingbros.ecommerce.customer.CustomerResponse;
import org.shippingbros.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
