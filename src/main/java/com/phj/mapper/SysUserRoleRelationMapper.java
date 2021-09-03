package com.phj.mapper;

import com.phj.pojo.SysRole;
import com.phj.pojo.SysUserRoleRelation;

import java.util.List;

/**
 * <p>
 * 用户与角色关系表 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface SysUserRoleRelationMapper extends EasyBaseMapper<SysUserRoleRelation> {
    public List<SysUserRoleRelation> baseSelect( SysUserRoleRelation param);

    public int baseUpdateByObjId(SysUserRoleRelation param);

    List<SysRole> selectByUid(Long id);
}
