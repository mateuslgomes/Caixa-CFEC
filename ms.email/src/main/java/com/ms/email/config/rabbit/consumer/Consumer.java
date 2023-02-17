package com.ms.email.config.rabbit.consumer;

import com.ms.email.config.rabbit.dto.EmailMqDto;
import com.ms.email.model.EmailModel;
import com.ms.email.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "API")
    private void consumidor(EmailMqDto emailMqDto) {
        EmailModel email = new EmailModel();
        BeanUtils.copyProperties(emailMqDto, email);
        emailService.sendEmail(email);
        System.out.println(email);
    }

}
