package com.phj.mapper;

import com.phj.pojo.SysPermission;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface SysPermissionMapper extends EasyBaseMapper<SysPermission> {
    public List<SysPermission> baseSelect( SysPermission param);

    public int baseUpdateByObjId(SysPermission param);
}
