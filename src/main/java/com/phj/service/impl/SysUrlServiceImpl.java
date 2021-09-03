package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.mapper.SysUrlMapper;
import com.phj.pojo.SysUrl;
import com.phj.service.SysUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 路由表 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class SysUrlServiceImpl implements SysUrlService {


       private final Logger logger = LoggerFactory.getLogger(SysUrlServiceImpl.class);

       @Autowired
       private SysUrlMapper sysUrlMapper;


      /**
      * @param id 通过id查询 SysUrl
      * @return SysUrl
      */
      @Override
      public SysUrl selectById(Long id) {
            return sysUrlMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 SysUrl
      * @return SysUrl
      */
      @Override
      public List<SysUrl> selectByIds(List<Long> ids) {
           return sysUrlMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<SysUrl> selectList(SysUrl param){
            Wrapper<SysUrl> wrapper = getWrapper(param);
            return sysUrlMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<SysUrl>
      */
      @Override
      public List<SysUrl> selectAll(){
            return  sysUrlMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<SysUrl> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<SysUrl>(currentNo,pageSize,true);
           return  sysUrlMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个SysUrl
      * @return List<SysUrl>
      */
      @Override
      public Page<SysUrl> selectPage(Integer currentNo, Integer pageSize, SysUrl param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
           Wrapper<SysUrl> wrapper = getWrapper(param);
           return sysUrlMapper.selectPage(new Page<>(currentNo,pageSize),wrapper);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<SysUrl> list) {
           Integer integer = sysUrlMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(SysUrl param) {
            int i = sysUrlMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(SysUrl param) {
           int i = sysUrlMapper.updateById(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = sysUrlMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = sysUrlMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 路由表条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<SysUrl> getWrapper(SysUrl param) {

        return Wrappers.<SysUrl>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, SysUrl::getId, param.getId())
                // 权限标识
                .eq(!StringUtils.isEmpty(param.getCode()), SysUrl::getCode, param.getCode())
                // 路由
                .eq(!StringUtils.isEmpty(param.getUrl()), SysUrl::getUrl, param.getUrl())
        ;
        }
}
