package com.phj.mapper;

import com.phj.pojo.DistributionClient;

import java.util.List;

/**
 * <p>
 * 配送客户 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface DistributionClientMapper extends EasyBaseMapper<DistributionClient> {
    public List<DistributionClient> baseSelect( DistributionClient param);

    public int baseUpdateByObjId(DistributionClient param);
}
