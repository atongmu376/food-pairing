package com.phj.mapper;

import com.phj.pojo.DistributionPath;

import java.util.List;

/**
 * <p>
 * 配送路线表 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface DistributionPathMapper extends EasyBaseMapper<DistributionPath> {
    public List<DistributionPath> baseSelect( DistributionPath param);

    public int baseUpdateByObjId(DistributionPath param);
}
