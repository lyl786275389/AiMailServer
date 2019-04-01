package com.liyulei.AiMail;

import com.liyulei.AiMail.service.MailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.time.LocalDateTime;

/**
 * @author liyulei
 * @date 2019-03-26 15:05
 */

@Component
@Configuration
@EnableScheduling


public class TimerSend {

    @Resource
    MailService mailService;

    @Scheduled(cron="*/5 * * * * ?")
    public void sendSimpleMail(){

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

        //mailService.sendSimpleMail("liyulei6@163.com","学习",str);
        System.out.println("执行静态定时任务时间: " + LocalDateTime.now());

    }



}
