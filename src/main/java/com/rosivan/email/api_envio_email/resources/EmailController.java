package com.rosivan.email.api_envio_email.resources;

import com.rosivan.email.api_envio_email.dtos.EmailDto;
import com.rosivan.email.api_envio_email.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void enviarEmail(@RequestBody EmailDto emailDto) {
        this.emailService.enviaEmail(emailDto);
    }
}
