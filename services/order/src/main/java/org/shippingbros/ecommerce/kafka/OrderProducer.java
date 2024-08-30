package org.shippingbros.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.shippingbros.ecommerce.config.KafkaOrderTopicConfig.ORDER_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation confirmation) {
        log.info("Sending order confirmation: {}", confirmation);
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(confirmation)
                .setHeader(KafkaHeaders.TOPIC, ORDER_TOPIC)
                .build();

        kafkaTemplate.send(message);

    }
}
