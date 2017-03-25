package com.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;

public class SendTest {


    public static void main(final String[] args) {
        final ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mail.xml",
                "applicationContext.xml");
        final MailSenderDemo sender = (MailSenderDemo)ac.getBean("MailSenderDemo");
        final SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("li2413674700@163.com");//收件人邮箱地址
        mail.setFrom("li2413674700@163.com");//收件人
        mail.setSubject("spring自带javamail发送的邮件");//主题
        mail.setText("hello this mail is from spring javaMail ");//正文
        sender.send(mail);
    }
}
