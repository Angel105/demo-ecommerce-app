package org.shippingbros.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

// We use here @Validated because record (Customer) is used inside other record (PaymentRequest)
@Validated
public record Customer(
        String id,
        @NotNull(message = "First name is required")
        String firstName,
        @NotNull(message = "Last name is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "The customer Email is not correctly formatted")
        String email
) {
}
