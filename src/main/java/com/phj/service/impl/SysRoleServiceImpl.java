package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.SysRoleMapper;
import com.phj.pojo.SysRole;
import com.phj.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {


       private final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

       @Autowired
       private SysRoleMapper sysRoleMapper;


      /**
      * @param id 通过id查询 SysRole
      * @return SysRole
      */
      @Override
      public SysRole selectById(Long id) {
            return sysRoleMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 SysRole
      * @return SysRole
      */
      @Override
      public List<SysRole> selectByIds(List<Long> ids) {
           return sysRoleMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<SysRole> selectList(SysRole param){
            Wrapper<SysRole> wrapper = getWrapper(param);
            return sysRoleMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<SysRole>
      */
      @Override
      public List<SysRole> selectAll(){
            return  sysRoleMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<SysRole> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<SysRole>(currentNo,pageSize,true);
           return  sysRoleMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个SysRole
      * @return List<SysRole>
      */
      @Override
      public Page<SysRole> selectPage(Integer currentNo, Integer pageSize, SysRole param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<SysRole> wrapper = getWrapper(param);
           return sysRoleMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<SysRole> list) {
           Integer integer = sysRoleMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(SysRole param) {
            int i = sysRoleMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(SysRole param) {
           int i = sysRoleMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = sysRoleMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = sysRoleMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 角色表条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<SysRole> getWrapper(SysRole param) {

        return Wrappers.<SysRole>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, SysRole::getId, param.getId())
                // 角色名
                .eq(!StringUtils.isEmpty(param.getName()), SysRole::getName, param.getName())
        ;
        }
}
