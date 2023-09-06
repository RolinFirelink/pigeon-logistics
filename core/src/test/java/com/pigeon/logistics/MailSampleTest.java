package com.pigeon.logistics;

import com.pigeon.logistics.service.MailService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MailSampleTest {

    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;

    @Test
    @Order(1)
    @DisplayName("发送简单邮件")
    public void sendSimpleMail() {
        mailService.sendSimpleMail("229200845@qq.com", "这是我的JAVA应用发送的邮件", "测试通过！");
    }

    @Test
    @Order(2)
    @DisplayName("发送带富文本的邮件")
    public void sendHtmlMail() throws MessagingException {
        var content = """
                <html>
                    <body>
                        <h3>Hello World! </h3>
                    </body>
                </html>
                """;
        mailService.sendHtmlMail("229200845@qq.com", "这是我的JAVA应用发送的富文本邮件", content);
    }

    @Test
    @Order(3)
    @DisplayName("用邮件模板发送邮件")
    public void sendHtmlMailWithTemplate() throws MessagingException {
        var context = new Context();
        context.setVariable("code", "zhlenysgcxa");
        var emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("229200845@qq.com", "这是我的JAVA应用发送的富文本邮件", emailContent);
    }

}
