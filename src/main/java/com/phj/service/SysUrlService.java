package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.SysUrl;

import java.util.List;

/**
* <p>
 * 路由表 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface SysUrlService {



 /**
 * @param id 通过id查询 SysUrl
 * @return SysUrl
 */
 public SysUrl selectById( Long id);

 /**
 * @param ids 通过id批量查询 SysUrl
 * @return SysUrl
 */
 public List<SysUrl>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个SysUrl
 * @return List<SysUrl>
 */
 public List<SysUrl> selectList(SysUrl param);

 /**
 * 查询All
 * @return List<SysUrl>
 */
 public List<SysUrl> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<SysUrl>
 */
 public Page<SysUrl> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个SysUrl
 * @return List<SysUrl>
 */
 public Page<SysUrl> selectPage(Integer currentNo,Integer pageSize,SysUrl param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<SysUrl> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(SysUrl param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(SysUrl param);

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

