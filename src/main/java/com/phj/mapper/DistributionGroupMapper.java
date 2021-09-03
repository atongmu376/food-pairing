package com.phj.mapper;

import com.phj.pojo.DistributionGroup;

import java.util.List;

/**
 * <p>
 * 配送小组 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface DistributionGroupMapper extends EasyBaseMapper<DistributionGroup> {
    public List<DistributionGroup> baseSelect( DistributionGroup param);

    public int baseUpdateByObjId(DistributionGroup param);
}
