package com.pigeon.logistics.service.impl;

import com.pigeon.logistics.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * @author dxy
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String from;

    public MailServiceImpl(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        log.info("发送静态邮件开始：from:{},to:{},subject:{},content:{}", from, to, subject, content);
        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        sender.send(simpleMailMessage);
        log.info("已发送");
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        log.info("发送静态邮件开始：from:{},to:{},subject:{},content:{}", from, to, subject, content);
        try {
            var message = sender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            sender.send(message);
        } catch (MessagingException e) {
            log.warn("邮件发送失败");
            throw new MessagingException(e.getMessage());
        }

        log.info("已发送");

    }
}
