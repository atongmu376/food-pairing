package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.PurchaseItemMapper;
import com.phj.pojo.PurchaseItem;
import com.phj.service.PurchaseItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 采购单明细 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {


       private final Logger logger = LoggerFactory.getLogger(PurchaseItemServiceImpl.class);

       @Autowired
       private PurchaseItemMapper purchaseItemMapper;


      /**
      * @param id 通过id查询 PurchaseItem
      * @return PurchaseItem
      */
      @Override
      public PurchaseItem selectById(Long id) {
            return purchaseItemMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 PurchaseItem
      * @return PurchaseItem
      */
      @Override
      public List<PurchaseItem> selectByIds(List<Long> ids) {
           return purchaseItemMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<PurchaseItem> selectList(PurchaseItem param){
          return purchaseItemMapper.selectByOrderId(param.getPurchaseId());
      }
      /**
      * 查询All
      * @return List<PurchaseItem>
      */
      @Override
      public List<PurchaseItem> selectAll(){
            return  purchaseItemMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<PurchaseItem> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<PurchaseItem>(currentNo,pageSize,true);
           return  purchaseItemMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个PurchaseItem
      * @return List<PurchaseItem>
      */
      @Override
      public Page<PurchaseItem> selectPage(Integer currentNo, Integer pageSize, PurchaseItem param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<PurchaseItem> wrapper = getWrapper(param);
           return purchaseItemMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<PurchaseItem> list) {
           Integer integer = purchaseItemMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(PurchaseItem param) {
            int i = purchaseItemMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(PurchaseItem param) {
           int i = purchaseItemMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = purchaseItemMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = purchaseItemMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 采购单明细条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<PurchaseItem> getWrapper(PurchaseItem param) {

        return Wrappers.<PurchaseItem>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, PurchaseItem::getId, param.getId())
                // 食材id
                .eq(param.getFoodId() != null, PurchaseItem::getFoodId, param.getFoodId())
                // 订单号
                .eq(param.getPurchaseId() != null, PurchaseItem::getPurchaseId, param.getPurchaseId())
                // 进货价
                .eq(param.getPurchasePrice() != null, PurchaseItem::getPurchasePrice, param.getPurchasePrice())
                // 进货数量
                .eq(param.getNum() != null, PurchaseItem::getNum, param.getNum())
        ;
        }
}
