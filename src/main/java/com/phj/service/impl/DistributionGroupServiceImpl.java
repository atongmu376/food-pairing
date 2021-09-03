package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.DistributionGroupMapper;
import com.phj.pojo.DistributionGroup;
import com.phj.service.DistributionGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 配送小组 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class DistributionGroupServiceImpl implements DistributionGroupService {


       private final Logger logger = LoggerFactory.getLogger(DistributionGroupServiceImpl.class);

       @Autowired
       private DistributionGroupMapper distributionGroupMapper;


      /**
      * @param id 通过id查询 DistributionGroup
      * @return DistributionGroup
      */
      @Override
      public DistributionGroup selectById(Long id) {
            return distributionGroupMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 DistributionGroup
      * @return DistributionGroup
      */
      @Override
      public List<DistributionGroup> selectByIds(List<Long> ids) {
           return distributionGroupMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<DistributionGroup> selectList(DistributionGroup param){
            Wrapper<DistributionGroup> wrapper = getWrapper(param);
            return distributionGroupMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<DistributionGroup>
      */
      @Override
      public List<DistributionGroup> selectAll(){
            return  distributionGroupMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<DistributionGroup> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<DistributionGroup>(currentNo,pageSize,true);
           return  distributionGroupMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个DistributionGroup
      * @return List<DistributionGroup>
      */
      @Override
      public Page<DistributionGroup> selectPage(Integer currentNo, Integer pageSize, DistributionGroup param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<DistributionGroup> wrapper = getWrapper(param);
           return distributionGroupMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<DistributionGroup> list) {
           Integer integer = distributionGroupMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(DistributionGroup param) {
            int i = distributionGroupMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(DistributionGroup param) {
           int i = distributionGroupMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = distributionGroupMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = distributionGroupMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 配送小组条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<DistributionGroup> getWrapper(DistributionGroup param) {

        return Wrappers.<DistributionGroup>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, DistributionGroup::getId, param.getId())
                // 小组名称
                .like(!StringUtils.isEmpty(param.getGroupName()), DistributionGroup::getGroupName, param.getGroupName())
        ;
        }
}
