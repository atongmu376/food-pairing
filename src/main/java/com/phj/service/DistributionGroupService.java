package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.DistributionGroup;

import java.util.List;

/**
* <p>
 * 配送小组 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface DistributionGroupService {



 /**
 * @param id 通过id查询 DistributionGroup
 * @return DistributionGroup
 */
 public DistributionGroup selectById( Long id);

 /**
 * @param ids 通过id批量查询 DistributionGroup
 * @return DistributionGroup
 */
 public List<DistributionGroup>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个DistributionGroup
 * @return List<DistributionGroup>
 */
 public List<DistributionGroup> selectList(DistributionGroup param);

 /**
 * 查询All
 * @return List<DistributionGroup>
 */
 public List<DistributionGroup> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<DistributionGroup>
 */
 public Page<DistributionGroup> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个DistributionGroup
 * @return List<DistributionGroup>
 */
 public Page<DistributionGroup> selectPage(Integer currentNo,Integer pageSize,DistributionGroup param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<DistributionGroup> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(DistributionGroup param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(DistributionGroup param);

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

