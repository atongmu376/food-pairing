package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.MealRank;

import java.util.List;

/**
* <p>
 * 餐别类型维护 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface MealRankService {



 /**
 * @param id 通过id查询 MealRank
 * @return MealRank
 */
 public MealRank selectById( Long id);

 /**
 * @param ids 通过id批量查询 MealRank
 * @return MealRank
 */
 public List<MealRank>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个MealRank
 * @return List<MealRank>
 */
 public List<MealRank> selectList(MealRank param);

 /**
 * 查询All
 * @return List<MealRank>
 */
 public List<MealRank> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<MealRank>
 */
 public Page<MealRank> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个MealRank
 * @return List<MealRank>
 */
 public Page<MealRank> selectPage(Integer currentNo,Integer pageSize,MealRank param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<MealRank> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(MealRank param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(MealRank param);

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

