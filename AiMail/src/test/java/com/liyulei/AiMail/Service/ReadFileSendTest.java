package com.liyulei.AiMail.Service;

import com.liyulei.AiMail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;

/**
 * @author liyulei
 * @date 2019-03-26 14:13
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadFileSendTest {

    @Resource
    MailService mailService;

    @Test
    public void sendSimpleMailTest(){

        String str = null;
        try {
            FileInputStream fs = new FileInputStream("/Users/didi/Desktop/AiMail/src/main/resources/static/News.txt");
            int size=fs.available();
            byte [] buffer = new byte[size];
            fs.read(buffer);
            fs.close();
            str=new String(buffer,"UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

        mailService.sendSimpleMail("liyulei6@163.com","学习",str);
    }

}
