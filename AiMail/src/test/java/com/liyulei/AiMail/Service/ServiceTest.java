package com.liyulei.AiMail.Service;

import com.liyulei.AiMail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

/**
 * @author liyulei
 * @date 2019-03-25 14:04
 */

@RunWith(SpringRunner.class)
@SpringBootTest

public class ServiceTest {

    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;


    @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail("liyulei6@163.com","学习","第一封邮件");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content = "<html>\n"+
                "<body>\n"+
                  "<h3>hello,world</h3>\n"+
                "</body>\n"+
                "</html>";
        mailService.sendHtmlMail("liyulei6@163.com","学习",content);
    }

    @Test
    public void sendAttachmentsMailTest() throws MessagingException {
        String filePath = "/Users/didi/Desktop/AiMail/src/main/resources/static/Girl.jpeg";
        mailService.sendAttachmentsMail("liyulei6@163.com","学习","Girl图片",filePath);
    }

    @Test
    public void sendInlineResouceMailTest() throws MessagingException {
        String imgPath = "/Users/didi/Desktop/AiMail/src/main/resources/static/Boy.jpg";
        String rscId = "Boy001";
        String content = "<html><body><img src=\'cid:"+rscId+"\'></img></body></html>";
        mailService.sendInlineResouceMail("liyulei6@163.com","图片邮件",content,imgPath,rscId);
    }

    @Test
    public void testTemplateMailTest() throws MessagingException {

        Context context = new Context();
        context.setVariable("id","u/679cb72ba1cd");

        String emailContext=templateEngine.process("emailTemplate",context);
        mailService.sendHtmlMail("liyulei6@163.com","学习",emailContext);

    }
}
