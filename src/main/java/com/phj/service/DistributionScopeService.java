package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.DistributionScope;

import java.util.List;

/**
* <p>
 * 配送区域 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface DistributionScopeService {



 /**
 * @param id 通过id查询 DistributionScope
 * @return DistributionScope
 */
 public DistributionScope selectById( Long id);

 /**
 * @param ids 通过id批量查询 DistributionScope
 * @return DistributionScope
 */
 public List<DistributionScope>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个DistributionScope
 * @return List<DistributionScope>
 */
 public List<DistributionScope> selectList(DistributionScope param);

 /**
 * 查询All
 * @return List<DistributionScope>
 */
 public List<DistributionScope> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<DistributionScope>
 */
 public Page<DistributionScope> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个DistributionScope
 * @return List<DistributionScope>
 */
 public Page<DistributionScope> selectPage(Integer currentNo,Integer pageSize,DistributionScope param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<DistributionScope> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(DistributionScope param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(DistributionScope param);

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

