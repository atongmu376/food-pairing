package com.phj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.ClientType;

import java.util.List;

/**
* <p>
 * 客户类型 服务类
 * </p>
*
* @author Mr.Pan
* @since 2021-08-19
*/
public interface ClientTypeService {



 /**
 * @param id 通过id查询 ClientType
 * @return ClientType
 */
 public ClientType selectById( Long id);

 /**
 * @param ids 通过id批量查询 ClientType
 * @return ClientType
 */
 public List<ClientType>  selectByIds(List<Long> ids);

 /**
 * @param param 通过param构建条件查询多个ClientType
 * @return List<ClientType>
 */
 public List<ClientType> selectList(ClientType param);

 /**
 * 查询All
 * @return List<ClientType>
 */
 public List<ClientType> selectAll();

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @return List<ClientType>
 */
 public Page<ClientType> selectPage(Integer currentNo,Integer pageSize);

 /**
 * @param currentNo 当前页码
 * @param pageSize 当前页显示条数
 * @param param 通过param构建条件查询多个ClientType
 * @return List<ClientType>
 */
 public Page<ClientType> selectPage(Integer currentNo,Integer pageSize,ClientType param);

 /**
 * @param list 批量新增
 * @return Integer 受影响条数
 */
 public  Integer insertList(List<ClientType> list);

 /**
 * @param param 单个新增
 * @return boolean
 */
 public  boolean insert(ClientType param);


 /**
 * @param param 必须带有主键
 * @return boolean
 */
 public boolean updateById(ClientType param);

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

