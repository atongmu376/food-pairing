package com.phj.mapper;

import com.phj.pojo.DistributionScope;

import java.util.List;

/**
 * <p>
 * 配送区域 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface DistributionScopeMapper extends EasyBaseMapper<DistributionScope> {
    public List<DistributionScope> baseSelect( DistributionScope param);

    public int baseUpdateByObjId(DistributionScope param);
}
