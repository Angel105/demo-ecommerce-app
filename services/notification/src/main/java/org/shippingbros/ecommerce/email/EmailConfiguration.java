package org.shippingbros.ecommerce.email;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Slf4j
public class EmailConfiguration {

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Value("${spring.mail.properties.mail.smtp.trust}")
    private String mailSmtpTrust;

    @Value("${spring.mail.properties.mail.auth}")
    private boolean mailSmtpAuth;

    @Value("${spring.mail.properties.mail.starttls.enabled}")
    private boolean mailSmtpStarttlsEnabled;

    @Value("${spring.mail.properties.mail.connectiontimeout}")
    private int mailSmtpConnectionTimeout;

    @Value("${spring.mail.properties.mail.timeout}")
    private int mailSmtpTimeout;

    @Value("${spring.mail.properties.mail.writetimeout}")
    private int mailSmtpWriteTimeout;

    @PostConstruct
    public void init() {
        log.info("Mail host is: {}", mailHost);
        log.info("Mail port is: {}", mailPort);
        log.info("Mail username is: {}", mailUsername);
        log.info("Mail password is: {}", mailPassword);
        log.info("Mail smtp trust is: {}", mailSmtpTrust);
        log.info("Mail smtp auth is: {}", mailSmtpAuth);
        log.info("Mail smtp starttls enabled is: {}", mailSmtpStarttlsEnabled);
        log.info("Mail smtp connection timeout is: {}", mailSmtpConnectionTimeout);
        log.info("Mail timeout is: {}", mailSmtpTimeout);
        log.info("Mail write timeout is: {}", mailSmtpWriteTimeout);
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", Boolean.toString(mailSmtpAuth));
        props.put("mail.smtp.trust", mailSmtpTrust);
        props.put("mail.smtp.starttls.enable", Boolean.toString(mailSmtpStarttlsEnabled));
        props.put("mail.smtp.connectiontimeout", Integer.toString(mailSmtpConnectionTimeout));
        props.put("mail.smtp.timeout", Integer.toString(mailSmtpTimeout));
        props.put("mail.smtp.writetimeout", Integer.toString(mailSmtpWriteTimeout));

        return mailSender;
    }

}
