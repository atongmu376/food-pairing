package com.phj.mapper;

import com.phj.pojo.SysUrl;

import java.util.List;

/**
 * <p>
 * 路由表 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface SysUrlMapper extends EasyBaseMapper<SysUrl> {
    public List<SysUrl> baseSelect( SysUrl param);

    public int baseUpdateByObjId(SysUrl param);
}
