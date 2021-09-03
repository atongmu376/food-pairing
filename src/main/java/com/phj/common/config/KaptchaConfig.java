package com.phj.common.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @program: food-pairing
 * @description: 验证码
 * @author: Mr.Pan
 * @create: 2021-08-29 14:55
 **/
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "64,158,255");
        properties.put("kaptcha.textproducer.char.space", "5");
        //如果需要生成算法验证码加上一下配置
        properties.put("kaptcha.textproducer.char.string", "1234567890");
        //如果需要去掉干扰线
//        properties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
