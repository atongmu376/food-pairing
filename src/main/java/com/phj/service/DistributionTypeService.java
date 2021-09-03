package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.DistributionType;

import java.util.List;

/**
* <p>
 * 配送类型 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface DistributionTypeService {



 /**
 * @param id 通过id查询 DistributionType
 * @return DistributionType
 */
 public DistributionType selectById( Long id);

 /**
 * @param ids 通过id批量查询 DistributionType
 * @return DistributionType
 */
 public List<DistributionType>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个DistributionType
 * @return List<DistributionType>
 */
 public List<DistributionType> selectList(DistributionType param);

 /**
 * 查询All
 * @return List<DistributionType>
 */
 public List<DistributionType> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<DistributionType>
 */
 public Page<DistributionType> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个DistributionType
 * @return List<DistributionType>
 */
 public Page<DistributionType> selectPage(Integer currentNo,Integer pageSize,DistributionType param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<DistributionType> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(DistributionType param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(DistributionType param);

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

