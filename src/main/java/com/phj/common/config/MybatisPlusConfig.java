package com.phj.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @program: food-pairing
 * @description:
 * @author: Mr.Pan
 * @create: 2021-08-16 14:19
 **/
@Configuration

public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        // DbType：数据库类型(根据类型获取应使用的分页方言)  // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        return interceptor;
    }

    @Bean
    public MySqlInjector getMySqlInjector(){
        return new MySqlInjector();
    }

    /**
     * 防止全表删除
     * @return
     */
    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor(){
        return new BlockAttackInnerInterceptor();
    }
}
