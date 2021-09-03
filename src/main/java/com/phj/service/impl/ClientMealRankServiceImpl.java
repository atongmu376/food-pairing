package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.ClientMealRankMapper;
import com.phj.pojo.ClientMealRank;
import com.phj.service.ClientMealRankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户餐别 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class ClientMealRankServiceImpl implements ClientMealRankService {


       private final Logger logger = LoggerFactory.getLogger(ClientMealRankServiceImpl.class);

       @Autowired
       private ClientMealRankMapper clientMealRankMapper;


      /**
      * @param id 通过id查询 ClientMealRank
      * @return ClientMealRank
      */
      @Override
      public ClientMealRank selectById(Long id) {



          return clientMealRankMapper.baseSelectById(id);
      }

    @Override
    public List<ClientMealRank> selectRankAndClient(Long mealRankId) {
        return clientMealRankMapper.selectRankAndClient(mealRankId);
    }

    /**
      * @param ids 通过id批量查询 ClientMealRank
      * @return ClientMealRank
      */
      @Override
      public List<ClientMealRank> selectByIds(List<Long> ids) {
           return clientMealRankMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<ClientMealRank> selectList(ClientMealRank param){


            return clientMealRankMapper.baseSelect(param);
      }
      /**
      * 查询All
      * @return List<ClientMealRank>
      */
      @Override
      public List<ClientMealRank> selectAll(){
            return  clientMealRankMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<ClientMealRank> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<ClientMealRank>(currentNo,pageSize,true);

           return  clientMealRankMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个ClientMealRank
      * @return List<ClientMealRank>
      */
      @Override
      public Page<ClientMealRank> selectPage(Integer currentNo, Integer pageSize, ClientMealRank param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<ClientMealRank> wrapper = getWrapper(param);

           return clientMealRankMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<ClientMealRank> list) {
           Integer integer = clientMealRankMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(ClientMealRank param) {
            int i = clientMealRankMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(ClientMealRank param) {
           int i = clientMealRankMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = clientMealRankMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = clientMealRankMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 客户餐别条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<ClientMealRank> getWrapper(ClientMealRank param) {

        return Wrappers.<ClientMealRank>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, ClientMealRank::getId, param.getId())
                // 客户id
                .eq(param.getClientId() != null, ClientMealRank::getClientId, param.getClientId())
                // 餐别id
                .eq(param.getMealRankId() != null, ClientMealRank::getMealRankId, param.getMealRankId())
                // 餐标（每餐价格）
                .eq(param.getMealPrice() != null, ClientMealRank::getMealPrice, param.getMealPrice())
                // 用餐人数
                .eq(param.getPersonNum() != null, ClientMealRank::getPersonNum, param.getPersonNum())
        ;
        }
}
