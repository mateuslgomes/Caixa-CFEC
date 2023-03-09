package com.microservice.email.controllers;

import com.microservice.email.dtos.EmailDto;
import com.microservice.email.models.EmailModel;
import com.microservice.email.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("sending-email")
    public void sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        emailService.sendEmail(new EmailModel(emailDto));
    }

}
