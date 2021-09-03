package com.phj.mapper;

import com.phj.pojo.Food;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 食材 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface FoodMapper extends EasyBaseMapper<Food> {
    //单表
    public List<Food> baseSelect( Food param);

    public int baseUpdateByObjId(Food param);

    //分页查询食材
    public List<Food> selectFoods(Food param);


    public List<Food> selectFoodsByIds(@Param("list") List<Long> list,@Param("isDelete")Integer idDelete);

    //    int updateBatch(@Param("content") Map<Long, Integer> map);
    int updateBatch(List<Food> list);
    int updateBatchStock(List<Food> list);
}
