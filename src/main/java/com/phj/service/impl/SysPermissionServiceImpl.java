package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.SysPermissionMapper;
import com.phj.pojo.SysPermission;
import com.phj.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {


       private final Logger logger = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

       @Autowired
       private SysPermissionMapper sysPermissionMapper;


      /**
      * @param id 通过id查询 SysPermission
      * @return SysPermission
      */
      @Override
      public SysPermission selectById(Long id) {
            return sysPermissionMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 SysPermission
      * @return SysPermission
      */
      @Override
      public List<SysPermission> selectByIds(List<Long> ids) {
           return sysPermissionMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<SysPermission> selectList(SysPermission param){
            Wrapper<SysPermission> wrapper = getWrapper(param);
            return sysPermissionMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<SysPermission>
      */
      @Override
      public List<SysPermission> selectAll(){
            return  sysPermissionMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<SysPermission> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<SysPermission>(currentNo,pageSize,true);
           return  sysPermissionMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个SysPermission
      * @return List<SysPermission>
      */
      @Override
      public Page<SysPermission> selectPage(Integer currentNo, Integer pageSize, SysPermission param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<SysPermission> wrapper = getWrapper(param);
           return sysPermissionMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<SysPermission> list) {
           Integer integer = sysPermissionMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(SysPermission param) {
            int i = sysPermissionMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(SysPermission param) {
           int i = sysPermissionMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = sysPermissionMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = sysPermissionMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 权限表条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<SysPermission> getWrapper(SysPermission param) {

        return Wrappers.<SysPermission>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, SysPermission::getId, param.getId())
                // 权限标识
                .eq(!StringUtils.isEmpty(param.getCode()), SysPermission::getCode, param.getCode())
                // 权限名
                .eq(!StringUtils.isEmpty(param.getName()), SysPermission::getName, param.getName())
        ;
        }
}
