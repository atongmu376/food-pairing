package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.MealOrderItemMapper;
import com.phj.pojo.MealOrderItem;
import com.phj.service.MealOrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 排餐订单明细 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class MealOrderItemServiceImpl implements MealOrderItemService {


       private final Logger logger = LoggerFactory.getLogger(MealOrderItemServiceImpl.class);

       @Autowired
       private MealOrderItemMapper mealOrderItemMapper;


      /**
      * @param id 通过id查询 MealOrderItem
      * @return MealOrderItem
      */
      @Override
      public MealOrderItem selectById(Long id) {
            return mealOrderItemMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 MealOrderItem
      * @return MealOrderItem
      */
      @Override
      public List<MealOrderItem> selectByIds(List<Long> ids) {
           return mealOrderItemMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<MealOrderItem> selectList(MealOrderItem param){

          return mealOrderItemMapper.baseSelect(param);
      }
      /**
      * 查询All
      * @return List<MealOrderItem>
      */
      @Override
      public List<MealOrderItem> selectAll(){
            return  mealOrderItemMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<MealOrderItem> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<MealOrderItem>(currentNo,pageSize,true);
           return  mealOrderItemMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个MealOrderItem
      * @return List<MealOrderItem>
      */
      @Override
      public Page<MealOrderItem> selectPage(Integer currentNo, Integer pageSize, MealOrderItem param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<MealOrderItem> wrapper = getWrapper(param);
           return mealOrderItemMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<MealOrderItem> list) {
           Integer integer = mealOrderItemMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(MealOrderItem param) {
            int i = mealOrderItemMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(MealOrderItem param) {
           int i = mealOrderItemMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = mealOrderItemMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = mealOrderItemMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 排餐订单明细条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<MealOrderItem> getWrapper(MealOrderItem param) {

        return Wrappers.<MealOrderItem>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, MealOrderItem::getId, param.getId())
                // 排餐订单号
                .eq(param.getMealPlanId() != null, MealOrderItem::getMealPlanId, param.getMealPlanId())
                // 食材id
                .eq(param.getFoodId() != null, MealOrderItem::getFoodId, param.getFoodId())
                // 销售价格
                .eq(param.getMarketPrice() != null, MealOrderItem::getMarketPrice, param.getMarketPrice())
                // 总量
                .eq(param.getTotalQuantity() != null, MealOrderItem::getTotalQuantity, param.getTotalQuantity())
        ;
        }
}
