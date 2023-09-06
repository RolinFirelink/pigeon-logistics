package com.pigeon.logistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 启动类
 *
 * @author dxy
 * @date 2022年3月19日
 */
@RestController
@EnableDiscoveryClient
@EnableCaching  // 开启缓存
@EnableStateMachine  // 开启Spring状态机
@EnableScheduling  // 开启定时任务
@EnableAsync  // 开启多线程
@SpringBootApplication
@MapperScan("com/pigeon/logistics/mapper")
public class LogisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticsApplication.class, args);
    }

    @RequestMapping
    public ModelAndView getStudent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

}
