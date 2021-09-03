package com.phj.mapper;

import com.phj.pojo.SysPermission;
import com.phj.pojo.SysRolePermissionRelation;

import java.util.List;

/**
 * <p>
 * 角色权限关系表 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface SysRolePermissionRelationMapper extends EasyBaseMapper<SysRolePermissionRelation> {
    public List<SysRolePermissionRelation> baseSelect( SysRolePermissionRelation param);

    public int baseUpdateByObjId(SysRolePermissionRelation param);

    List<SysPermission> selectByRids(List<Long> rids);
}
