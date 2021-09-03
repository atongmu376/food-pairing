package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.FoodUnitMapper;
import com.phj.pojo.FoodUnit;
import com.phj.service.FoodUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 食材计量单位 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class FoodUnitServiceImpl implements FoodUnitService {


       private final Logger logger = LoggerFactory.getLogger(FoodUnitServiceImpl.class);

       @Autowired
       private FoodUnitMapper foodUnitMapper;


      /**
      * @param id 通过id查询 FoodUnit
      * @return FoodUnit
      */
      @Override
      public FoodUnit selectById(Long id) {
            return foodUnitMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 FoodUnit
      * @return FoodUnit
      */
      @Override
      public List<FoodUnit> selectByIds(List<Long> ids) {
           return foodUnitMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<FoodUnit> selectList(FoodUnit param){
            Wrapper<FoodUnit> wrapper = getWrapper(param);
            return foodUnitMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<FoodUnit>
      */
      @Override
      public List<FoodUnit> selectAll(){
            return  foodUnitMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<FoodUnit> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<FoodUnit>(currentNo,pageSize,true);
           return  foodUnitMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个FoodUnit
      * @return List<FoodUnit>
      */
      @Override
      public Page<FoodUnit> selectPage(Integer currentNo, Integer pageSize, FoodUnit param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<FoodUnit> wrapper = getWrapper(param);
           return foodUnitMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<FoodUnit> list) {
           Integer integer = foodUnitMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(FoodUnit param) {
            int i = foodUnitMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(FoodUnit param) {
           int i = foodUnitMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = foodUnitMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = foodUnitMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 食材计量单位条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<FoodUnit> getWrapper(FoodUnit param) {

        return Wrappers.<FoodUnit>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, FoodUnit::getId, param.getId())
                // 单位
                .like(!StringUtils.isEmpty(param.getName()), FoodUnit::getName, param.getName())
        ;
        }
}
