package com.rosivan.email.api_envio_email.service;

import com.rosivan.email.api_envio_email.dtos.EmailDto;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviaEmail(EmailDto emailDto) {
            /*
            var message = new SimpleMailMessage();
            message.setFrom("noreply@gmail.com");
            message.setTo(emailDto.email());
            message.setSubject("Rosivan Impl Envia email");
            message.setText(emailDto.texto());

            this.javaMailSender.send(message);
            */

            try {
                MimeMessage message = this.javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setFrom("noreply@gmail.com");
                helper.setSubject("Rosivan Impl Envia email");
                helper.setTo(emailDto.email());

                String template = this.carregaTemplateEmail();

                template = template.replace("#{nome}", emailDto.nome());

                helper.setText(template, true);

                this.javaMailSender.send(message);

            } catch (Exception exception) {
                System.out.println("=========================================================");
                System.out.println("Falha ao enviar email");
                System.out.println("=========================================================");
            }
    }

    public String carregaTemplateEmail() throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/template-email.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
