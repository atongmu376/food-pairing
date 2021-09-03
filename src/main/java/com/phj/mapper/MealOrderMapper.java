package com.phj.mapper;

import com.phj.dto.MealOrderSelect;
import com.phj.pojo.MealOrder;

import java.util.List;

/**
 * <p>
 * 排餐订单 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface MealOrderMapper extends EasyBaseMapper<MealOrder> {
    /**
     通过MealOrder对象查询
     */
    public List<MealOrder> baseSelect( MealOrder param);

    /**
     通过id查询
     */
    public MealOrder baseSelectById( Long param);

    /**
     通过ids查询多个
     */
    public List<MealOrder> baseSelectByIds( List<Long> param);

    /**
     通过MealOrder对象查询单个 如果满足多个返回第一条
     */
    public MealOrder baseSelectByEntity( MealOrder param);

    /**
     通过MealOrder对象修改单个
     */
    public int baseUpdateByObjId(MealOrder param);

    public List<MealOrder> selectOrderInfo(MealOrderSelect select);

}
