package com.phj.mapper;

import com.phj.pojo.DistributionType;

import java.util.List;

/**
 * <p>
 * 配送类型 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface DistributionTypeMapper extends EasyBaseMapper<DistributionType> {
    public List<DistributionType> baseSelect( DistributionType param);

    public int baseUpdateByObjId(DistributionType param);
}
