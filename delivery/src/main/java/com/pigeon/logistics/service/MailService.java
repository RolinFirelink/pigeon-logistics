package com.pigeon.logistics.service;

import javax.mail.MessagingException;

public interface MailService {
    /**
     * 发送简单邮件
     *
     * @param to      收信人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送带有富文本的邮件
     *
     * @param to      收信人
     * @param subject 主题
     * @param content 内容
     */
    void sendHtmlMail(String to, String subject, String content) throws MessagingException;

    // 发送带有附件的邮件

    // 发送图片邮件

}
