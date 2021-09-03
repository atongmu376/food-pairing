package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.phj.pojo.Purchase;

import java.util.List;

/**
* <p>
 * 供应商 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface PurchaseService {



 /**
 * @param id 通过id查询 Purchase
 * @return Purchase
 */
 public Purchase selectById( Long id);

 /**
 * @param ids 通过id批量查询 Purchase
 * @return Purchase
 */
 public List<Purchase>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个Purchase
 * @return List<Purchase>
 */
 public List<Purchase> selectList(Purchase param);

 /**
 * 查询All
 * @return List<Purchase>
 */
 public List<Purchase> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<Purchase>
 */
 public Page<Purchase> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个Purchase
 * @return List<Purchase>
 */
 public PageInfo<Purchase> selectPage(Integer currentNo, Integer pageSize, Purchase param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<Purchase> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(Purchase param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(Purchase param);

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

