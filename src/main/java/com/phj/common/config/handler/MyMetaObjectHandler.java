package com.phj.common.config.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @program: food-pairing
 * @description:
 * @author: Mr.Pan
 * @create: 2021-08-16 14:25
 **/

@Slf4j
//mybatis-plus字段填充器
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createDate", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateDate", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
    }
}

