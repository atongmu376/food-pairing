package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.SysRolePermissionRelation;

import java.util.List;

/**
* <p>
 * 角色权限关系表 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface SysRolePermissionRelationService {



 /**
 * @param id 通过id查询 SysRolePermissionRelation
 * @return SysRolePermissionRelation
 */
 public SysRolePermissionRelation selectById( Long id);

 /**
 * @param ids 通过id批量查询 SysRolePermissionRelation
 * @return SysRolePermissionRelation
 */
 public List<SysRolePermissionRelation>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个SysRolePermissionRelation
 * @return List<SysRolePermissionRelation>
 */
 public List<SysRolePermissionRelation> selectList(SysRolePermissionRelation param);

 /**
 * 查询All
 * @return List<SysRolePermissionRelation>
 */
 public List<SysRolePermissionRelation> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<SysRolePermissionRelation>
 */
 public Page<SysRolePermissionRelation> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个SysRolePermissionRelation
 * @return List<SysRolePermissionRelation>
 */
 public Page<SysRolePermissionRelation> selectPage(Integer currentNo,Integer pageSize,SysRolePermissionRelation param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<SysRolePermissionRelation> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(SysRolePermissionRelation param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(SysRolePermissionRelation param);

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

