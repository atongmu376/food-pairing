package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.DistributionTypeMapper;
import com.phj.pojo.DistributionType;
import com.phj.service.DistributionTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 配送类型 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class DistributionTypeServiceImpl implements DistributionTypeService {


       private final Logger logger = LoggerFactory.getLogger(DistributionTypeServiceImpl.class);

       @Autowired
       private DistributionTypeMapper distributionTypeMapper;


      /**
      * @param id 通过id查询 DistributionType
      * @return DistributionType
      */
      @Override
      public DistributionType selectById(Long id) {
            return distributionTypeMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 DistributionType
      * @return DistributionType
      */
      @Override
      public List<DistributionType> selectByIds(List<Long> ids) {
           return distributionTypeMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<DistributionType> selectList(DistributionType param){
            Wrapper<DistributionType> wrapper = getWrapper(param);
            return distributionTypeMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<DistributionType>
      */
      @Override
      public List<DistributionType> selectAll(){
            return  distributionTypeMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<DistributionType> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<DistributionType>(currentNo,pageSize,true);
           return  distributionTypeMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个DistributionType
      * @return List<DistributionType>
      */
      @Override
      public Page<DistributionType> selectPage(Integer currentNo, Integer pageSize, DistributionType param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<DistributionType> wrapper = getWrapper(param);
           return distributionTypeMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<DistributionType> list) {
           Integer integer = distributionTypeMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(DistributionType param) {
            int i = distributionTypeMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(DistributionType param) {
           int i = distributionTypeMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = distributionTypeMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = distributionTypeMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 配送类型条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<DistributionType> getWrapper(DistributionType param) {

        return Wrappers.<DistributionType>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, DistributionType::getId, param.getId())
                // 类型名称
                .like(!StringUtils.isEmpty(param.getName()), DistributionType::getName, param.getName())
                // 提前配送天数
                .eq(param.getDay() != null, DistributionType::getDay, param.getDay())
        ;
        }
}
