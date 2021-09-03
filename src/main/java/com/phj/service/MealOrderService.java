package com.phj.service;

import com.github.pagehelper.PageInfo;
import com.phj.dto.MealOrderSelect;
import com.phj.pojo.MealOrder;

import java.util.List;

/**
* <p>
 * 排餐订单 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface MealOrderService {



 /**
 * @param id 通过id查询 MealOrder
 * @return MealOrder
 */
 public MealOrder selectById( Long id);

 /**
 * @param ids 通过id批量查询 MealOrder
 * @return MealOrder
 */
 public List<MealOrder>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个MealOrder
 * @return List<MealOrder>
 */
 public List<MealOrder> selectList(MealOrder param);

 /**
 * 查询All
 * @return List<MealOrder>
 */
 public List<MealOrder> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param select
  * @return List<MealOrder>
 */
 public PageInfo<MealOrder> selectPage(Integer currentNo, Integer pageSize, MealOrderSelect select);


 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<MealOrder> list);



 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(MealOrder param);

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


 public   boolean carriage(Long id);
}

