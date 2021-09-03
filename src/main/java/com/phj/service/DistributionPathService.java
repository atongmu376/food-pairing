package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.DistributionPath;

import java.util.List;

/**
* <p>
 * 配送路线表 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface DistributionPathService {



 /**
 * @param id 通过id查询 DistributionPath
 * @return DistributionPath
 */
 public DistributionPath selectById( Long id);

 /**
 * @param ids 通过id批量查询 DistributionPath
 * @return DistributionPath
 */
 public List<DistributionPath>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个DistributionPath
 * @return List<DistributionPath>
 */
 public List<DistributionPath> selectList(DistributionPath param);

 /**
 * 查询All
 * @return List<DistributionPath>
 */
 public List<DistributionPath> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<DistributionPath>
 */
 public Page<DistributionPath> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个DistributionPath
 * @return List<DistributionPath>
 */
 public Page<DistributionPath> selectPage(Integer currentNo,Integer pageSize,DistributionPath param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<DistributionPath> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(DistributionPath param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(DistributionPath param);

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

