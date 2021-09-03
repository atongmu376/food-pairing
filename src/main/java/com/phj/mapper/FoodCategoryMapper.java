package com.phj.mapper;

import com.phj.pojo.FoodCategory;

import java.util.List;

/**
 * <p>
 * 食材分类 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface FoodCategoryMapper extends EasyBaseMapper<FoodCategory> {
    public List<FoodCategory> baseSelect( FoodCategory param);

    public int baseUpdateByObjId(FoodCategory param);
}
