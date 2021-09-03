package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.Supplier;

import java.util.List;

/**
* <p>
 * 供应商 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface SupplierService {



 /**
 * @param id 通过id查询 Supplier
 * @return Supplier
 */
 public Supplier selectById( Long id);

 /**
 * @param ids 通过id批量查询 Supplier
 * @return Supplier
 */
 public List<Supplier>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个Supplier
 * @return List<Supplier>
 */
 public List<Supplier> selectList(Supplier param);

 /**
 * 查询All
 * @return List<Supplier>
 */
 public List<Supplier> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<Supplier>
 */
 public Page<Supplier> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个Supplier
 * @return List<Supplier>
 */
 public Page<Supplier> selectPage(Integer currentNo,Integer pageSize,Supplier param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<Supplier> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(Supplier param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(Supplier param);

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

