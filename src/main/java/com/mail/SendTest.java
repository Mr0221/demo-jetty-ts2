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
        mail.setTo("li2413674700@163.com");//�ռ��������ַ
        mail.setFrom("li2413674700@163.com");//�ռ���
        mail.setSubject("spring�Դ�javamail���͵��ʼ�");//����
        mail.setText("hello this mail is from spring javaMail ");//����
        sender.send(mail);
    }
}
