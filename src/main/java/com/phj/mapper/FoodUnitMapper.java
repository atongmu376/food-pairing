package com.phj.mapper;

import com.phj.pojo.FoodUnit;

import java.util.List;

/**
 * <p>
 * 食材计量单位 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface FoodUnitMapper extends EasyBaseMapper<FoodUnit> {
    public List<FoodUnit> baseSelect( FoodUnit param);

    public int baseUpdateByObjId(FoodUnit param);
}
