package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.ClientMealRank;

import java.util.List;

/**
* <p>
 * 客户餐别 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface ClientMealRankService {



 /**
 * @param id 通过id查询 ClientMealRank
 * @return ClientMealRank
 */
 public ClientMealRank selectById( Long id);
 public List<ClientMealRank> selectRankAndClient(Long mealRankId);



 /**
 * @param ids 通过id批量查询 ClientMealRank
 * @return ClientMealRank
 */
 public List<ClientMealRank>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个ClientMealRank
 * @return List<ClientMealRank>
 */
 public List<ClientMealRank> selectList(ClientMealRank param);

 /**
 * 查询All
 * @return List<ClientMealRank>
 */
 public List<ClientMealRank> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<ClientMealRank>
 */
 public Page<ClientMealRank> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个ClientMealRank
 * @return List<ClientMealRank>
 */
 public Page<ClientMealRank> selectPage(Integer currentNo,Integer pageSize,ClientMealRank param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<ClientMealRank> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(ClientMealRank param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(ClientMealRank param);

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

