package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phj.mapper.DistributionClientMapper;
import com.phj.pojo.DistributionClient;
import com.phj.service.DistributionClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 配送客户 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-24
 */
@Service
public class DistributionClientServiceImpl implements DistributionClientService {


       private final Logger logger = LoggerFactory.getLogger(DistributionClientServiceImpl.class);

       @Autowired
       private DistributionClientMapper distributionClientMapper;


      /**
      * @param id 通过id查询 DistributionClient
      * @return DistributionClient
      */
      @Override
      public DistributionClient selectById(Long id) {
            return distributionClientMapper.selectById(id);
      }

      /**
      * @param ids 通过id批量查询 DistributionClient
      * @return DistributionClient
      */
      @Override
      public List<DistributionClient> selectByIds(List<Long> ids) {
           return distributionClientMapper.selectBatchIds(ids);
      }

      /**
      param构建条件
      */
      @Override
      public List<DistributionClient> selectList(DistributionClient param){
            Wrapper<DistributionClient> wrapper = getWrapper(param);
            return distributionClientMapper.selectList(wrapper);
      }
      /**
      * 查询All
      * @return List<DistributionClient>
      */
      @Override
      public List<DistributionClient> selectAll(){
            return  distributionClientMapper.selectList(null);
      }
      /**
      分页查询所有
      */
      @Override
      public Page<DistributionClient> selectPage(Integer currentNo, Integer pageSize) {
           if(currentNo<=0){
                 currentNo=1;
           }
           if(pageSize<=0){
                 pageSize=10;
           }
           Page page=new Page<DistributionClient>(currentNo,pageSize,true);
           return  distributionClientMapper.selectPage(page,null);
      }

      /**
      * @param param 通过param构建条件查询多个DistributionClient
      * @return List<DistributionClient>
      */
      @Override
      public PageInfo<DistributionClient> selectPage(Integer currentNo, Integer pageSize, DistributionClient param) {
           if(currentNo<=0){
               currentNo=1;
           }
           if(pageSize<=0){
               pageSize=10;
           }
          PageHelper.startPage(currentNo, pageSize, true);
          List<DistributionClient> distributionClients = distributionClientMapper.baseSelect(param);

          return PageInfo.of(distributionClients);
      }



      /**
      * @param list 批量新增
      * @return Integer 受影响条数
      */
      @Override
      public Integer insertList(List<DistributionClient> list) {
           Integer integer = distributionClientMapper.insertBatchSomeColumn(list);
           return integer;
      }
      /**
      * @param param 单个新增
      * @return boolean
      */
      @Override
      public boolean insert(DistributionClient param) {
            int i = distributionClientMapper.insert(param);
            return  i<1?false:true;
      }

      /**
      * @param param 必须带有主键
      * @return boolean
      */
      @Override
      public boolean updateById(DistributionClient param) {
          int i = distributionClientMapper.baseUpdateByObjId(param);
           return  i<1?false:true;
      }

      /**
      * @param id 通过id删除
      * @return boolean
      */
      @Override
      public boolean deleteById(Long id) {
           int i = distributionClientMapper.deleteById(id);
           return  i<1?false:true;
      }

      /**
      * @param ids 通过id集合批量删除
      * @return boolean
      */
      @Override
      public boolean deleteByIds(List<Long> ids) {
        int i = distributionClientMapper.deleteBatchIds(ids);
        return  i<1?false:true;
      }


        /**
        * 配送客户条件列表
        * @param param 根据需要进行传值
        * @return
        */
        public Wrapper<DistributionClient> getWrapper(DistributionClient param) {

        return Wrappers.<DistributionClient>lambdaUpdate()
        // 主键
                .eq(param.getId() != null, DistributionClient::getId, param.getId())
                // 客户名称
                .eq(!StringUtils.isEmpty(param.getName()), DistributionClient::getName, param.getName())
                // 联系人名称
                .eq(!StringUtils.isEmpty(param.getPhoneName()), DistributionClient::getPhoneName, param.getPhoneName())
                // 联系电话
                .eq(!StringUtils.isEmpty(param.getPhoneNum()), DistributionClient::getPhoneNum, param.getPhoneNum())
                // 配送地址
                .eq(!StringUtils.isEmpty(param.getAddress()), DistributionClient::getAddress, param.getAddress())
                // 0普通，1vip1推类
                .eq(param.getClientType() != null, DistributionClient::getClientType, param.getClientType())
                // 配送类型id
                .eq(param.getTypeId() != null, DistributionClient::getTypeId, param.getTypeId())
                // 配送小组
                .eq(!StringUtils.isEmpty(param.getGroupId()), DistributionClient::getGroupId, param.getGroupId())
                // 配送路线
                .eq(!StringUtils.isEmpty(param.getPathId()), DistributionClient::getPathId, param.getPathId())
                // 配送区域
                .eq(!StringUtils.isEmpty(param.getScopeId()), DistributionClient::getScopeId, param.getScopeId())
                // 逻辑删除(0代表未删除，1代表删除)
                .eq(param.getIsDelete() != null, DistributionClient::getIsDelete, param.getIsDelete())
                // 配货分拣(0代表分拣，1不分拣)
                .eq(param.getDistributionSorting() != null, DistributionClient::getDistributionSorting, param.getDistributionSorting())
        ;
        }
}
