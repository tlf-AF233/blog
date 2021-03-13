package com.af.blog.service;

public interface MailService {

    /**
     * 发送邮件
     * @param text 文本内容
     * @param email 收件邮箱
     */
    void sendEmail(String text, String email);
}
