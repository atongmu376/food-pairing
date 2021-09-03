package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.SysUserRoleRelation;

import java.util.List;

/**
* <p>
 * 用户与角色关系表 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface SysUserRoleRelationService {



 /**
 * @param id 通过id查询 SysUserRoleRelation
 * @return SysUserRoleRelation
 */
 public SysUserRoleRelation selectById( Long id);

 /**
 * @param ids 通过id批量查询 SysUserRoleRelation
 * @return SysUserRoleRelation
 */
 public List<SysUserRoleRelation>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个SysUserRoleRelation
 * @return List<SysUserRoleRelation>
 */
 public List<SysUserRoleRelation> selectList(SysUserRoleRelation param);

 /**
 * 查询All
 * @return List<SysUserRoleRelation>
 */
 public List<SysUserRoleRelation> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<SysUserRoleRelation>
 */
 public Page<SysUserRoleRelation> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个SysUserRoleRelation
 * @return List<SysUserRoleRelation>
 */
 public Page<SysUserRoleRelation> selectPage(Integer currentNo,Integer pageSize,SysUserRoleRelation param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<SysUserRoleRelation> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(SysUserRoleRelation param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(SysUserRoleRelation param);

 /**
 * @param id 通过id删除
 * @return boolean
 */
 public  boolean deleteById(Long id);

 /**
 * @param ids 通过id集合批量删除
 * @return boolean
 */
 public  boolean deleteByIds(List<Long> ids);



}

