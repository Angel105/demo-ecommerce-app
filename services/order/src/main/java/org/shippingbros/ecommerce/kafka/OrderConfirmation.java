package org.shippingbros.ecommerce.kafka;

import org.shippingbros.ecommerce.customer.CustomerResponse;
import org.shippingbros.ecommerce.order.PaymentMethod;
import org.shippingbros.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
