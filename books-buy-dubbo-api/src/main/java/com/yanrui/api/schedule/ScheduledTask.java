package com.yanrui.api.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-29 17:40
 **/
@Component
public class ScheduledTask {

    @Scheduled(fixedRate = 30000)
    public void scheduledTask() {
        System.out.println("任务执行时间：" + LocalDateTime.now());
    }

}