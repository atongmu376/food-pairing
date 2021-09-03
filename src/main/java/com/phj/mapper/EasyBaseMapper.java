package com.phj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * @program: jurisdiction
 * @description: 开启批量查询
 * @author: Mr.Pan
 * @create: 2021-07-31 15:10
 **/

public interface EasyBaseMapper<T> extends  BaseMapper<T> {
    /**
     * 批量插入 仅适用于mysql
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);

}
