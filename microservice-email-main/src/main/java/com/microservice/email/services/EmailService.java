package com.microservice.email.services;

import com.microservice.email.enums.StatusEmail;
import com.microservice.email.models.EmailModel;
import com.microservice.email.repositories.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class EmailService  {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender mailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            mailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
            log.info("Email sent: {}", emailModel);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
            log.error("Error sending email: {}", emailModel, e);
        }
        return emailRepository.save(emailModel);
    }

}
