package com.phj.mapper;

import com.phj.pojo.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface SysRoleMapper extends EasyBaseMapper<SysRole> {
    public List<SysRole> baseSelect( SysRole param);

    public int baseUpdateByObjId(SysRole param);
}
