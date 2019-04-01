package com.liyulei.AiMail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author liyulei
 * @date 2019-03-25 12:40
 */

@Service
public class MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String to,String subject,String content){

        SimpleMailMessage message = new SimpleMailMessage();

        logger.info("发送文本邮件开始:{},{},{}",to,subject,content);

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);

        logger.info("文本图片邮件发送成功");
    }

    public void sendHtmlMail(String to,String subject,String content){

        MimeMessage message = mailSender.createMimeMessage();

        logger.info("发送Html邮件开始:{},{},{}",to,subject,content);

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);

            mailSender.send(message);

            logger.info("Html图片邮件发送成功");

        } catch (MessagingException e) {
            logger.error("邮件发送失败:",e);
        }

    }

    public void sendAttachmentsMail(String to,String subject,String content,String filePath){

        MimeMessage message = mailSender.createMimeMessage();

        logger.info("发送附件邮件开始:{},{},{},{}",to,subject,content,filePath);

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName= file.getFilename();
            helper.addAttachment(fileName,file);

            mailSender.send(message);

            logger.info("附件邮件发送成功");

        } catch (MessagingException e) {
            logger.error("邮件发送失败:",e);
        }


    }

    public void sendInlineResouceMail(String to,String subject,String content,String rscPath,String rscId){

        MimeMessage message = mailSender.createMimeMessage();

        logger.info("发送静态邮件开始:{},{},{},{},{}",to,subject,content,rscPath,rscId);
        try {
            MimeMessageHelper helper  = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId,res);

            mailSender.send(message);

            logger.info("静态图片邮件发送成功:");
        } catch (MessagingException e) {
            logger.error("邮件发送失败:",e);
        }

    }
}
