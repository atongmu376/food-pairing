package com.phj.common.util;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @program: food-pairing
 * @description:
 * @author: Mr.Pan
 * @create: 2021-09-02 08:45
 **/
@Service
@EnableScheduling
public class SchedulingService {

    @Scheduled(fixedDelay = 5000) //每五秒
    public void  job(){
        System.out.println("热舞");

    }

    @Scheduled(cron = "0 0 0 * * ?") //每天凌晨
    public void  job1(){
        System.out.println("在具体时间执行");
    }



}
