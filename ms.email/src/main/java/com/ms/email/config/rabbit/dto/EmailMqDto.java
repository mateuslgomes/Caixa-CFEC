package com.ms.email.config.rabbit.dto;

import lombok.Data;

@Data
public class EmailMqDto {

    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;

}
