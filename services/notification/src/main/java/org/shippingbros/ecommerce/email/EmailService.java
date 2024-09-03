package org.shippingbros.ecommerce.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shippingbros.ecommerce.kafka.order.Product;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.shippingbros.ecommerce.email.EmailTemplates.ORDER_CONFIRMATION;
import static org.shippingbros.ecommerce.email.EmailTemplates.PAYMENT_CONFIRMATION;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private static final String FROM_EMAIL = "siteandstock@gmail.com";
    private static final String EMAIL_SEND_SUCCESS_LOG_MSG = "INFO - Email successfully sent to {} with template {}";
    private static final String EMAIL_SEND_FAILURE_LOG_MSG = "WARNING - Cannot send email to {}";

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference

    ) throws MessagingException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        sendEmail(destinationEmail, PAYMENT_CONFIRMATION.getTemplate(),
                PAYMENT_CONFIRMATION.getSubject(), variables);
    }

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products

    ) throws MessagingException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        sendEmail(destinationEmail, ORDER_CONFIRMATION.getTemplate(),
                ORDER_CONFIRMATION.getSubject(), variables);

    }


    private void sendEmail(
            String recipientEmail,
            String templateName,
            String subject,
            Map<String, Object> variables
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name());
        messageHelper.setFrom(FROM_EMAIL);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(subject);
        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(recipientEmail);
            mailSender.send(mimeMessage);
            log.info(EMAIL_SEND_SUCCESS_LOG_MSG, recipientEmail, templateName);
        } catch (MessagingException e) {
            log.warn(EMAIL_SEND_FAILURE_LOG_MSG, recipientEmail);
        }
    }

}
