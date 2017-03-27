package com.haomiao.portal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/10/14.
 */

@Component
@SpringBootApplication
public class SpringBootTaskTest {

    @Scheduled(cron = "* 0/20 * * * ?") // 每20秒执行一次
    public void cc(){
            System.out.println("---------------           --------------");
    }

}
