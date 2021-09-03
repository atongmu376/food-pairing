package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.MealOrderItem;

import java.util.List;

/**
* <p>
 * 排餐订单明细 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface MealOrderItemService {



 /**
 * @param id 通过id查询 MealOrderItem
 * @return MealOrderItem
 */
 public MealOrderItem selectById( Long id);

 /**
 * @param ids 通过id批量查询 MealOrderItem
 * @return MealOrderItem
 */
 public List<MealOrderItem>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个MealOrderItem
 * @return List<MealOrderItem>
 */
 public List<MealOrderItem> selectList(MealOrderItem param);

 /**
 * 查询All
 * @return List<MealOrderItem>
 */
 public List<MealOrderItem> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<MealOrderItem>
 */
 public Page<MealOrderItem> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个MealOrderItem
 * @return List<MealOrderItem>
 */
 public Page<MealOrderItem> selectPage(Integer currentNo,Integer pageSize,MealOrderItem param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<MealOrderItem> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(MealOrderItem param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(MealOrderItem param);

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

