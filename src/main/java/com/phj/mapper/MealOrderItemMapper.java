package com.phj.mapper;

import com.phj.pojo.MealOrderItem;

import java.util.List;

/**
 * <p>
 * 排餐订单明细 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface MealOrderItemMapper extends EasyBaseMapper<MealOrderItem> {
    public List<MealOrderItem> baseSelect( MealOrderItem param);

    public int baseUpdateByObjId(MealOrderItem param);
}
