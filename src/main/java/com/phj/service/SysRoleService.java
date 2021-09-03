package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.SysRole;

import java.util.List;

/**
* <p>
 * 角色表 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface SysRoleService {



 /**
 * @param id 通过id查询 SysRole
 * @return SysRole
 */
 public SysRole selectById( Long id);

 /**
 * @param ids 通过id批量查询 SysRole
 * @return SysRole
 */
 public List<SysRole>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个SysRole
 * @return List<SysRole>
 */
 public List<SysRole> selectList(SysRole param);

 /**
 * 查询All
 * @return List<SysRole>
 */
 public List<SysRole> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<SysRole>
 */
 public Page<SysRole> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个SysRole
 * @return List<SysRole>
 */
 public Page<SysRole> selectPage(Integer currentNo,Integer pageSize,SysRole param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<SysRole> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(SysRole param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(SysRole param);

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

