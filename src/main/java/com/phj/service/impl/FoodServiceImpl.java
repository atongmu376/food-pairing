package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phj.mapper.FoodMapper;
import com.phj.pojo.Food;
import com.phj.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 食材 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class FoodServiceImpl implements FoodService {


       private final Logger logger = LoggerFactory.getLogger(FoodServiceImpl.class);

       @Autowired
       private FoodMapper foodMapper;


      /**
      * @param id 通过id查询 Food
      * @return Food
      */
      @Override
      public Food selectById(Long id) {
            return foodMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 Food
      * @return Food
      */
      @Override
      public List<Food> selectByIds(List<Long> ids) {
           return foodMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<Food> selectList(Food param){
            Wrapper<Food> wrapper = getWrapper(param);
            return foodMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<Food>
      */
      @Override
      public List<Food> selectAll(){
            return  foodMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<Food> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<Food>(currentNo,pageSize,true);
           return  foodMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个Food
      * @return List<Food>
      */
      @Override
      public PageInfo<Food> selectPage(Integer currentNo, Integer pageSize, Food param) {
          if(currentNo<=0){
              currentNo=1;
          }
          if(pageSize<=0){
              pageSize=10;
          }
          PageHelper.startPage(currentNo,pageSize);
          List<Food> foods = foodMapper.selectFoods(param);

          return PageInfo.of(foods);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<Food> list) {
           Integer integer = foodMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(Food param) {
            int i = foodMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(Food param) {
           int i = foodMapper.baseUpdateByObjId(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = foodMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = foodMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 食材条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<Food> getWrapper(Food param) {

        return Wrappers.<Food>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, Food::getId, param.getId())
                // 食物名称
                .like(!StringUtils.isEmpty(param.getName()), Food::getName, param.getName())
                // 分类id
                .eq(param.getCategoryId() != null, Food::getCategoryId, param.getCategoryId())
                // 规格
                .eq(!StringUtils.isEmpty(param.getSpecification()), Food::getSpecification, param.getSpecification())
                // 品牌
                .like(!StringUtils.isEmpty(param.getBrand()), Food::getBrand, param.getBrand())
                // 分配单位（克）
                .eq(!StringUtils.isEmpty(param.getUnit()), Food::getUnit, param.getUnit())
                .eq(!StringUtils.isEmpty(param.getUnitId()), Food::getUnitId, param.getUnitId())
                // 进货价格
                // 图片
                .eq(!StringUtils.isEmpty(param.getImage()), Food::getImage, param.getImage())
                //单位表id
                .eq(param.getMarketPrice() != null, Food::getMarketPrice, param.getMarketPrice())
                // 销售价格
                .eq(param.getPurchasePrice() != null, Food::getPurchasePrice, param.getPurchasePrice())
                // 库存
                .eq(param.getStock() != null, Food::getStock, param.getStock())
                // 乐观锁
                .eq(param.getUpdateVersion() != null, Food::getUpdateVersion, param.getUpdateVersion())
                // 逻辑删除(0代表未删除，1代表删除)
                .eq(param.getIsDelete() != null, Food::getIsDelete, param.getIsDelete())
        ;
        }
}
