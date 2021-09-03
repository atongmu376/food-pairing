package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.DistributionScopeMapper;
import com.phj.pojo.DistributionScope;
import com.phj.service.DistributionScopeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 配送区域 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class DistributionScopeServiceImpl implements DistributionScopeService {


       private final Logger logger = LoggerFactory.getLogger(DistributionScopeServiceImpl.class);

       @Autowired
       private DistributionScopeMapper distributionScopeMapper;


      /**
      * @param id 通过id查询 DistributionScope
      * @return DistributionScope
      */
      @Override
      public DistributionScope selectById(Long id) {
            return distributionScopeMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 DistributionScope
      * @return DistributionScope
      */
      @Override
      public List<DistributionScope> selectByIds(List<Long> ids) {
           return distributionScopeMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<DistributionScope> selectList(DistributionScope param){
            Wrapper<DistributionScope> wrapper = getWrapper(param);
            return distributionScopeMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<DistributionScope>
      */
      @Override
      public List<DistributionScope> selectAll(){
            return  distributionScopeMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<DistributionScope> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<DistributionScope>(currentNo,pageSize,true);
           return  distributionScopeMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个DistributionScope
      * @return List<DistributionScope>
      */
      @Override
      public Page<DistributionScope> selectPage(Integer currentNo, Integer pageSize, DistributionScope param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<DistributionScope> wrapper = getWrapper(param);
           return distributionScopeMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<DistributionScope> list) {
           Integer integer = distributionScopeMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(DistributionScope param) {
            int i = distributionScopeMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(DistributionScope param) {
           int i = distributionScopeMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = distributionScopeMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = distributionScopeMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 配送区域条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<DistributionScope> getWrapper(DistributionScope param) {

        return Wrappers.<DistributionScope>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, DistributionScope::getId, param.getId())
                // 配送范围
                .like(!StringUtils.isEmpty(param.getScope()), DistributionScope::getScope, param.getScope())
        ;
        }
}
