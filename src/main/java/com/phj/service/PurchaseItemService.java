package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.PurchaseItem;

import java.util.List;

/**
* <p>
 * 采购单明细 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface PurchaseItemService {



 /**
 * @param id 通过id查询 PurchaseItem
 * @return PurchaseItem
 */
 public PurchaseItem selectById( Long id);

 /**
 * @param ids 通过id批量查询 PurchaseItem
 * @return PurchaseItem
 */
 public List<PurchaseItem>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个PurchaseItem
 * @return List<PurchaseItem>
 */
 public List<PurchaseItem> selectList(PurchaseItem param);

 /**
 * 查询All
 * @return List<PurchaseItem>
 */
 public List<PurchaseItem> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<PurchaseItem>
 */
 public Page<PurchaseItem> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个PurchaseItem
 * @return List<PurchaseItem>
 */
 public Page<PurchaseItem> selectPage(Integer currentNo,Integer pageSize,PurchaseItem param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<PurchaseItem> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(PurchaseItem param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(PurchaseItem param);

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

