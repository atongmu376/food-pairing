package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.SysUser;

import java.util.List;

/**
* <p>
 * 用户表 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface SysUserService {


 /**
 * @param id 通过id查询 SysUser
 * @return SysUser
 */
 public SysUser selectById( Long id);

 public SysUser selectOne(SysUser sysUser);

 /**
 * @param ids 通过id批量查询 SysUser
 * @return SysUser
 */
 public List<SysUser>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个SysUser
 * @return List<SysUser>
 */
 public List<SysUser> selectList(SysUser param);

 /**
 * 查询All
 * @return List<SysUser>
 */
 public List<SysUser> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<SysUser>
 */
 public Page<SysUser> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个SysUser
 * @return List<SysUser>
 */
 public Page<SysUser> selectPage(Integer currentNo,Integer pageSize,SysUser param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<SysUser> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(SysUser param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(SysUser param);



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

