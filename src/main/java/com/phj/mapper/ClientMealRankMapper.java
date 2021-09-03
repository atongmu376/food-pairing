package com.phj.mapper;

import com.phj.pojo.ClientMealRank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户餐别 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface ClientMealRankMapper extends EasyBaseMapper<ClientMealRank> {
    //查询 ClientMealRank 和 tb_meal_rank 表  客户餐别与餐别的对照关系
    public List<ClientMealRank> baseSelect( ClientMealRank param);

    // 通过餐别id查询餐别和对应多个客户
    public List<ClientMealRank> selectRankAndClient(Long  mealRankId);

    public int baseUpdateByObjId(ClientMealRank param);

    public ClientMealRank baseSelectById(Long id);

    //批量校验客户餐别信息
    int checkClientMealRank(@Param("meal") ClientMealRank one,@Param("list") List<Long> id);
}
