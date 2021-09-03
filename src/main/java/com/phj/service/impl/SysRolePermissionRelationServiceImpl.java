package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.SysRolePermissionRelationMapper;
import com.phj.pojo.SysRolePermissionRelation;
import com.phj.service.SysRolePermissionRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色权限关系表 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class SysRolePermissionRelationServiceImpl implements SysRolePermissionRelationService {


       private final Logger logger = LoggerFactory.getLogger(SysRolePermissionRelationServiceImpl.class);

       @Autowired
       private SysRolePermissionRelationMapper sysRolePermissionRelationMapper;


      /**
      * @param id 通过id查询 SysRolePermissionRelation
      * @return SysRolePermissionRelation
      */
      @Override
      public SysRolePermissionRelation selectById(Long id) {
            return sysRolePermissionRelationMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 SysRolePermissionRelation
      * @return SysRolePermissionRelation
      */
      @Override
      public List<SysRolePermissionRelation> selectByIds(List<Long> ids) {
           return sysRolePermissionRelationMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<SysRolePermissionRelation> selectList(SysRolePermissionRelation param){
            Wrapper<SysRolePermissionRelation> wrapper = getWrapper(param);
            return sysRolePermissionRelationMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<SysRolePermissionRelation>
      */
      @Override
      public List<SysRolePermissionRelation> selectAll(){
            return  sysRolePermissionRelationMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<SysRolePermissionRelation> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<SysRolePermissionRelation>(currentNo,pageSize,true);
           return  sysRolePermissionRelationMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个SysRolePermissionRelation
      * @return List<SysRolePermissionRelation>
      */
      @Override
      public Page<SysRolePermissionRelation> selectPage(Integer currentNo, Integer pageSize, SysRolePermissionRelation param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<SysRolePermissionRelation> wrapper = getWrapper(param);
           return sysRolePermissionRelationMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<SysRolePermissionRelation> list) {
           Integer integer = sysRolePermissionRelationMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(SysRolePermissionRelation param) {
            int i = sysRolePermissionRelationMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(SysRolePermissionRelation param) {
           int i = sysRolePermissionRelationMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = sysRolePermissionRelationMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = sysRolePermissionRelationMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 角色权限关系表条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<SysRolePermissionRelation> getWrapper(SysRolePermissionRelation param) {

        return Wrappers.<SysRolePermissionRelation>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, SysRolePermissionRelation::getId, param.getId())
                // 角色id
                .eq(param.getRoleId() != null, SysRolePermissionRelation::getRoleId, param.getRoleId())
                // 用户id
                .eq(param.getPermissionId() != null, SysRolePermissionRelation::getPermissionId, param.getPermissionId())
        ;
        }
}
