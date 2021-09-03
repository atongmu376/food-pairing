package com.phj.mapper;

import com.phj.pojo.SysUser;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface SysUserMapper extends EasyBaseMapper<SysUser> {
    public List<SysUser> baseSelect( SysUser param);

    public int baseUpdateByObjId(SysUser param);

    public  List<SysUser> selectAllInfo();

}
