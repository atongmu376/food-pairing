package com.phj.mapper;

import com.phj.pojo.MealRank;

import java.util.List;

/**
 * <p>
 * 餐别类型维护 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface MealRankMapper extends EasyBaseMapper<MealRank> {
    public List<MealRank> baseSelect( MealRank param);

    public int baseUpdateByObjId(MealRank param);
}
