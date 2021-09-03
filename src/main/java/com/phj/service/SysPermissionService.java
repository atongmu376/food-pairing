package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.SysPermission;

import java.util.List;

/**
* <p>
 * 权限表 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface SysPermissionService {



 /**
 * @param id 通过id查询 SysPermission
 * @return SysPermission
 */
 public SysPermission selectById( Long id);

 /**
 * @param ids 通过id批量查询 SysPermission
 * @return SysPermission
 */
 public List<SysPermission>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个SysPermission
 * @return List<SysPermission>
 */
 public List<SysPermission> selectList(SysPermission param);

 /**
 * 查询All
 * @return List<SysPermission>
 */
 public List<SysPermission> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<SysPermission>
 */
 public Page<SysPermission> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个SysPermission
 * @return List<SysPermission>
 */
 public Page<SysPermission> selectPage(Integer currentNo,Integer pageSize,SysPermission param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<SysPermission> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(SysPermission param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(SysPermission param);

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

