package com.phj.common.config;

import com.phj.common.config.handler.JsonObjectArgResolverHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @program: food-pairing
 * @description:
 * @author: Mr.Pan
 * @create: 2021-08-29 16:24
 **/

@Configuration
@EnableWebMvc
public class ApplicationConfigurer implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 添加MultiRequestBody参数解析器
        argumentResolvers.add(new JsonObjectArgResolverHandler());
    }
//
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        // 解决中文乱码问题
//        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
//    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(responseBodyConverter());
//    }
}
