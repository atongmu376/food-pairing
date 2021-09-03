package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.FoodCategoryMapper;
import com.phj.pojo.FoodCategory;
import com.phj.service.FoodCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 食材分类 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {


       private final Logger logger = LoggerFactory.getLogger(FoodCategoryServiceImpl.class);

       @Autowired
       private FoodCategoryMapper foodCategoryMapper;


      /**
      * @param id 通过id查询 FoodCategory
      * @return FoodCategory
      */
      @Override
      public FoodCategory selectById(Long id) {
            return foodCategoryMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 FoodCategory
      * @return FoodCategory
      */
      @Override
      public List<FoodCategory> selectByIds(List<Long> ids) {
           return foodCategoryMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<FoodCategory> selectList(FoodCategory param){
            Wrapper<FoodCategory> wrapper = getWrapper(param);
            return foodCategoryMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<FoodCategory>
      */
      @Override
      public List<FoodCategory> selectAll(){
            return  foodCategoryMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<FoodCategory> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<FoodCategory>(currentNo,pageSize,true);
           return  foodCategoryMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个FoodCategory
      * @return List<FoodCategory>
      */
      @Override
      public Page<FoodCategory> selectPage(Integer currentNo, Integer pageSize, FoodCategory param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<FoodCategory> wrapper = getWrapper(param);
           return foodCategoryMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<FoodCategory> list) {
           Integer integer = foodCategoryMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(FoodCategory param) {
            int i = foodCategoryMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(FoodCategory param) {
           int i = foodCategoryMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = foodCategoryMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = foodCategoryMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 食材分类条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<FoodCategory> getWrapper(FoodCategory param) {

        return Wrappers.<FoodCategory>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, FoodCategory::getId, param.getId())
                // 分类名称
                .like(!StringUtils.isEmpty(param.getName()), FoodCategory::getName, param.getName())
        ;
        }
}
