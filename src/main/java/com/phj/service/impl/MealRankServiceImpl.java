package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.MealRankMapper;
import com.phj.pojo.MealRank;
import com.phj.service.MealRankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 餐别类型维护 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class MealRankServiceImpl implements MealRankService {


       private final Logger logger = LoggerFactory.getLogger(MealRankServiceImpl.class);

       @Autowired
       private MealRankMapper mealRankMapper;


      /**
      * @param id 通过id查询 MealRank
      * @return MealRank
      */
      @Override
      public MealRank selectById(Long id) {
            return mealRankMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 MealRank
      * @return MealRank
      */
      @Override
      public List<MealRank> selectByIds(List<Long> ids) {
           return mealRankMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<MealRank> selectList(MealRank param){
            Wrapper<MealRank> wrapper = getWrapper(param);
            return mealRankMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<MealRank>
      */
      @Override
      public List<MealRank> selectAll(){
            return  mealRankMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<MealRank> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<MealRank>(currentNo,pageSize,true);
           return  mealRankMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个MealRank
      * @return List<MealRank>
      */
      @Override
      public Page<MealRank> selectPage(Integer currentNo, Integer pageSize, MealRank param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<MealRank> wrapper = getWrapper(param);
           return mealRankMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<MealRank> list) {
           Integer integer = mealRankMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(MealRank param) {
            int i = mealRankMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(MealRank param) {
           int i = mealRankMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = mealRankMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = mealRankMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 餐别类型维护条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<MealRank> getWrapper(MealRank param) {

        return Wrappers.<MealRank>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, MealRank::getId, param.getId())
                // 名称
                .eq(!StringUtils.isEmpty(param.getName()), MealRank::getName, param.getName())
                // 显示顺序
                .eq(param.getSeq() != null, MealRank::getSeq, param.getSeq())
                // 统计分类
                .eq(!StringUtils.isEmpty(param.getCategoryName()), MealRank::getCategoryName, param.getCategoryName())
        ;
        }
}
