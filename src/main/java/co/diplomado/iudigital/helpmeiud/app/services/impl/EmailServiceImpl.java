package co.diplomado.iudigital.helpmeiud.app.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import co.diplomado.iudigital.helpmeiud.app.services.ifaces.IEmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender sender;

    @Override
    public boolean sendMail(String mensaje, String email, String asunto) {
        boolean sent = false;
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        try {
            mimeMessageHelper.setFrom("noreply@iudigital.edu.co");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(mensaje);
            mimeMessageHelper.setSubject(asunto);
            sender.send(message);
            sent = true;
            log.info("Mensaje enviado exitosamente!");
        } catch (MessagingException e) {
            log.info("Error al enviar mensaje {}", e.getMessage());
        }
        return sent;
    }
}
