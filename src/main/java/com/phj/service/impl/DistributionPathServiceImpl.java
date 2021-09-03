package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.DistributionPathMapper;
import com.phj.pojo.DistributionPath;
import com.phj.service.DistributionPathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 配送路线表 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class DistributionPathServiceImpl implements DistributionPathService {


       private final Logger logger = LoggerFactory.getLogger(DistributionPathServiceImpl.class);

       @Autowired
       private DistributionPathMapper distributionPathMapper;


      /**
      * @param id 通过id查询 DistributionPath
      * @return DistributionPath
      */
      @Override
      public DistributionPath selectById(Long id) {
            return distributionPathMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 DistributionPath
      * @return DistributionPath
      */
      @Override
      public List<DistributionPath> selectByIds(List<Long> ids) {
           return distributionPathMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<DistributionPath> selectList(DistributionPath param){
            Wrapper<DistributionPath> wrapper = getWrapper(param);
            return distributionPathMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<DistributionPath>
      */
      @Override
      public List<DistributionPath> selectAll(){
            return  distributionPathMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<DistributionPath> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<DistributionPath>(currentNo,pageSize,true);
           return  distributionPathMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个DistributionPath
      * @return List<DistributionPath>
      */
      @Override
      public Page<DistributionPath> selectPage(Integer currentNo, Integer pageSize, DistributionPath param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<DistributionPath> wrapper = getWrapper(param);
           return distributionPathMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<DistributionPath> list) {
           Integer integer = distributionPathMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(DistributionPath param) {
            int i = distributionPathMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(DistributionPath param) {
           int i = distributionPathMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = distributionPathMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = distributionPathMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 配送路线表条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<DistributionPath> getWrapper(DistributionPath param) {

        return Wrappers.<DistributionPath>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, DistributionPath::getId, param.getId())
                // 配送地址
                .like(!StringUtils.isEmpty(param.getAddress()), DistributionPath::getAddress, param.getAddress())
        ;
        }
}
