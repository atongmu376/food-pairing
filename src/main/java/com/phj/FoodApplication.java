package com.phj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: food-pairing
 * @description:
 * @author: Mr.Pan
 * @create: 2021-08-16 13:35
 **/

@SpringBootApplication
@MapperScan("com.phj.mapper")
//@ComponentScan(basePackages = "com.phj.common.config")
public class FoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodApplication.class, args);
    }
}
