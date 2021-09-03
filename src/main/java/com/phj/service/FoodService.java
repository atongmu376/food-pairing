package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.phj.pojo.Food;

import java.util.List;

/**
* <p>
 * 食材 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface FoodService {



 /**
 * @param id 通过id查询 Food
 * @return Food
 */
 public Food selectById( Long id);

 /**
 * @param ids 通过id批量查询 Food
 * @return Food
 */
 public List<Food>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个Food
 * @return List<Food>
 */
 public List<Food> selectList(Food param);

 /**
 * 查询All
 * @return List<Food>
 */
 public List<Food> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<Food>
 */
 public Page<Food> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个Food
 * @return List<Food>
 */
 public PageInfo<Food> selectPage(Integer currentNo, Integer pageSize, Food param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<Food> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(Food param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(Food param);

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

