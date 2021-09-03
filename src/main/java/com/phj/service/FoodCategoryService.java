package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.FoodCategory;

import java.util.List;

/**
* <p>
 * 食材分类 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface FoodCategoryService {



 /**
 * @param id 通过id查询 FoodCategory
 * @return FoodCategory
 */
 public FoodCategory selectById( Long id);

 /**
 * @param ids 通过id批量查询 FoodCategory
 * @return FoodCategory
 */
 public List<FoodCategory>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个FoodCategory
 * @return List<FoodCategory>
 */
 public List<FoodCategory> selectList(FoodCategory param);

 /**
 * 查询All
 * @return List<FoodCategory>
 */
 public List<FoodCategory> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<FoodCategory>
 */
 public Page<FoodCategory> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个FoodCategory
 * @return List<FoodCategory>
 */
 public Page<FoodCategory> selectPage(Integer currentNo,Integer pageSize,FoodCategory param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<FoodCategory> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(FoodCategory param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(FoodCategory param);

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

