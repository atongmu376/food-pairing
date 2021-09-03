package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.FoodUnit;

import java.util.List;

/**
* <p>
 * 食材计量单位 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface FoodUnitService {



 /**
 * @param id 通过id查询 FoodUnit
 * @return FoodUnit
 */
 public FoodUnit selectById( Long id);

 /**
 * @param ids 通过id批量查询 FoodUnit
 * @return FoodUnit
 */
 public List<FoodUnit>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个FoodUnit
 * @return List<FoodUnit>
 */
 public List<FoodUnit> selectList(FoodUnit param);

 /**
 * 查询All
 * @return List<FoodUnit>
 */
 public List<FoodUnit> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<FoodUnit>
 */
 public Page<FoodUnit> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个FoodUnit
 * @return List<FoodUnit>
 */
 public Page<FoodUnit> selectPage(Integer currentNo,Integer pageSize,FoodUnit param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<FoodUnit> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(FoodUnit param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(FoodUnit param);

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

