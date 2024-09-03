package org.shippingbros.ecommerce.kafka;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shippingbros.ecommerce.email.EmailService;
import org.shippingbros.ecommerce.kafka.order.OrderConfirmation;
import org.shippingbros.ecommerce.kafka.payment.PaymentConfirmation;
import org.shippingbros.ecommerce.notification.Notification;
import org.shippingbros.ecommerce.notification.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.shippingbros.ecommerce.notification.NotificationType.ORDER_CONFIRMATION;
import static org.shippingbros.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    public static final String PAYMENT_TOPIC = "payment-topic";
    public static final String ORDER_TOPIC = "order-topic";
    private static final String CONSUME_MESSAGE_LOG = "Consuming the message from {}:: {}";

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = PAYMENT_TOPIC)
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        logMessage(PAYMENT_TOPIC, paymentConfirmation);
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        // send email to our customer
        var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }

    @KafkaListener(topics = ORDER_TOPIC)
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        logMessage(ORDER_TOPIC, orderConfirmation);
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        // send email to our customer
        var customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }

    private void logMessage(String topic, Object message) {
        log.info(CONSUME_MESSAGE_LOG, topic, message);
    }

}
