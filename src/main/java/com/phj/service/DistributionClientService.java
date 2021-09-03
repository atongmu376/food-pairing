package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.phj.pojo.DistributionClient;

import java.util.List;

/**
* <p>
 * 配送客户 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface DistributionClientService {



 /**
 * @param id 通过id查询 DistributionClient
 * @return DistributionClient
 */
 public DistributionClient selectById( Long id);

 /**
 * @param ids 通过id批量查询 DistributionClient
 * @return DistributionClient
 */
 public List<DistributionClient>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个DistributionClient
 * @return List<DistributionClient>
 */
 public List<DistributionClient> selectList(DistributionClient param);

 /**
 * 查询All
 * @return List<DistributionClient>
 */
 public List<DistributionClient> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<DistributionClient>
 */
 public Page<DistributionClient> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个DistributionClient
 * @return List<DistributionClient>
 */
 public PageInfo<DistributionClient> selectPage(Integer currentNo, Integer pageSize, DistributionClient param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<DistributionClient> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(DistributionClient param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(DistributionClient param);

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

