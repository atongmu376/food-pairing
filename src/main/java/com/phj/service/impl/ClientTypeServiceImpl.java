package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.ClientTypeMapper;
import com.phj.pojo.ClientType;
import com.phj.service.ClientTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 客户类型 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class ClientTypeServiceImpl implements ClientTypeService {


       private final Logger logger = LoggerFactory.getLogger(ClientTypeServiceImpl.class);

       @Autowired
       private ClientTypeMapper clientTypeMapper;


      /**
      * @param id 通过id查询 ClientType
      * @return ClientType
      */
      @Override
      public ClientType selectById(Long id) {
            return clientTypeMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 ClientType
      * @return ClientType
      */
      @Override
      public List<ClientType> selectByIds(List<Long> ids) {
           return clientTypeMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<ClientType> selectList(ClientType param){
            Wrapper<ClientType> wrapper = getWrapper(param);
            return clientTypeMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<ClientType>
      */
      @Override
      public List<ClientType> selectAll(){
            return  clientTypeMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<ClientType> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<ClientType>(currentNo,pageSize,true);
           return  clientTypeMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个ClientType
      * @return List<ClientType>
      */
      @Override
      public Page<ClientType> selectPage(Integer currentNo, Integer pageSize, ClientType param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<ClientType> wrapper = getWrapper(param);
           return clientTypeMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<ClientType> list) {
           Integer integer = clientTypeMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(ClientType param) {
            int i = clientTypeMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(ClientType param) {
           int i = clientTypeMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = clientTypeMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = clientTypeMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 客户类型条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<ClientType> getWrapper(ClientType param) {

        return Wrappers.<ClientType>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, ClientType::getId, param.getId())
                // 客户类型名称
                .like(!StringUtils.isEmpty(param.getTypeName()), ClientType::getTypeName, param.getTypeName())
        ;
        }
}
