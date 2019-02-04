package com.dcoj.service;

public interface MailService {
    /**
     * 发送邮箱验证
     * @param to 邮件收件人
     * @param subject 邮件主题
     * @param verifyCode 验证码
     */
    public void sendMail(String to, String subject,String verifyCode);
}
