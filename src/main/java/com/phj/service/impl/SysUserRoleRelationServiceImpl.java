package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.SysUserRoleRelationMapper;
import com.phj.pojo.SysUserRoleRelation;
import com.phj.service.SysUserRoleRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户与角色关系表 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class SysUserRoleRelationServiceImpl implements SysUserRoleRelationService {


       private final Logger logger = LoggerFactory.getLogger(SysUserRoleRelationServiceImpl.class);

       @Autowired
       private SysUserRoleRelationMapper sysUserRoleRelationMapper;


      /**
      * @param id 通过id查询 SysUserRoleRelation
      * @return SysUserRoleRelation
      */
      @Override
      public SysUserRoleRelation selectById(Long id) {
            return sysUserRoleRelationMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 SysUserRoleRelation
      * @return SysUserRoleRelation
      */
      @Override
      public List<SysUserRoleRelation> selectByIds(List<Long> ids) {
           return sysUserRoleRelationMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<SysUserRoleRelation> selectList(SysUserRoleRelation param){
            Wrapper<SysUserRoleRelation> wrapper = getWrapper(param);
            return sysUserRoleRelationMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<SysUserRoleRelation>
      */
      @Override
      public List<SysUserRoleRelation> selectAll(){
            return  sysUserRoleRelationMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<SysUserRoleRelation> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<SysUserRoleRelation>(currentNo,pageSize,true);
           return  sysUserRoleRelationMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个SysUserRoleRelation
      * @return List<SysUserRoleRelation>
      */
      @Override
      public Page<SysUserRoleRelation> selectPage(Integer currentNo, Integer pageSize, SysUserRoleRelation param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<SysUserRoleRelation> wrapper = getWrapper(param);
           return sysUserRoleRelationMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<SysUserRoleRelation> list) {
           Integer integer = sysUserRoleRelationMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(SysUserRoleRelation param) {
            int i = sysUserRoleRelationMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(SysUserRoleRelation param) {
           int i = sysUserRoleRelationMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = sysUserRoleRelationMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = sysUserRoleRelationMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 用户与角色关系表条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<SysUserRoleRelation> getWrapper(SysUserRoleRelation param) {

        return Wrappers.<SysUserRoleRelation>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, SysUserRoleRelation::getId, param.getId())
                // 角色id
                .eq(param.getRoleId() != null, SysUserRoleRelation::getRoleId, param.getRoleId())
                // 用户id
                .eq(param.getUserId() != null, SysUserRoleRelation::getUserId, param.getUserId())
        ;
        }
}
