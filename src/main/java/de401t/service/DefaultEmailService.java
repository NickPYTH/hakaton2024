package de401t.service;

import de401t.model.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DefaultEmailService implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendSimpleEmail(String toAddress, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("admin@request-desk.ru");
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }

    public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) throws MessagingException, IOException, InvalidFormatException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom("admin@request-desk.ru");
        messageHelper.setTo("nicksrnk@gmail.com");
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        File file = new File(attachment);
        FileSystemResource fileE = new FileSystemResource(file);
        //messageHelper.addAttachment("Заявка.xlsx", fileE);
        emailSender.send(mimeMessage);
    }

    public void sendEmailForGroup(List<User> userList, String subject, String message) {
        for(User user : userList) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("admin@request-desk.ru");
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            emailSender.send(simpleMailMessage);
        }
    }

}