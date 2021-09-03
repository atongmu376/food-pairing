package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.SupplierMapper;
import com.phj.pojo.Supplier;
import com.phj.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 供应商 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class SupplierServiceImpl implements SupplierService {


       private final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

       @Autowired
       private SupplierMapper supplierMapper;


      /**
      * @param id 通过id查询 Supplier
      * @return Supplier
      */
      @Override
      public Supplier selectById(Long id) {
            return supplierMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 Supplier
      * @return Supplier
      */
      @Override
      public List<Supplier> selectByIds(List<Long> ids) {
           return supplierMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<Supplier> selectList(Supplier param){
            Wrapper<Supplier> wrapper = getWrapper(param);
            return supplierMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<Supplier>
      */
      @Override
      public List<Supplier> selectAll(){
            return  supplierMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<Supplier> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<Supplier>(currentNo,pageSize,true);
           return  supplierMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个Supplier
      * @return List<Supplier>
      */
      @Override
      public Page<Supplier> selectPage(Integer currentNo, Integer pageSize, Supplier param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<Supplier> wrapper = getWrapper(param);
           return supplierMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<Supplier> list) {
           Integer integer = supplierMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(Supplier param) {
            int i = supplierMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(Supplier param) {
           int i = supplierMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
          Supplier supplier = new Supplier();
          supplier.setId(id);
          supplier.setIsDelete(1);
          int i=supplierMapper.baseUpdateByObjId(supplier);
          return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = supplierMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 供应商条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<Supplier> getWrapper(Supplier param) {

        return Wrappers.<Supplier>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, Supplier::getId, param.getId())
                // 供应商名称
                .like(!StringUtils.isEmpty(param.getName()), Supplier::getName, param.getName())
                // 手机号
                .eq(!StringUtils.isEmpty(param.getPhoneNum()), Supplier::getPhoneNum, param.getPhoneNum())
                // 地址
                .like(!StringUtils.isEmpty(param.getAddress()), Supplier::getAddress, param.getAddress())
                // 资质图片
                .eq(!StringUtils.isEmpty(param.getCertificationImage()), Supplier::getCertificationImage, param.getCertificationImage())
                // 到期时间
                .eq(param.getExpiration() != null, Supplier::getExpiration, param.getExpiration())
                // 逻辑删除  (0代表未删除，1代表删除)
                .eq(param.getIsDelete() != null, Supplier::getIsDelete, param.getIsDelete())
        ;
        }
}
