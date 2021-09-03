package com.phj.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.common.util.SHA256Util;
import com.phj.mapper.SysUserMapper;
import com.phj.pojo.SysUser;
import com.phj.service.SysUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    private final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * @param id 通过id查询 SysUser
     * @return SysUser
     */
    @Override
    public SysUser selectById(Long id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public SysUser selectOne(SysUser sysUser) {
        Wrapper<SysUser> wrapper = getWrapper(sysUser);
        return sysUserMapper.selectOne(wrapper);
    }

    /**
     * @param ids 通过id批量查询 SysUser
     * @return SysUser
     */
    @Override
    public List<SysUser> selectByIds(List<Long> ids) {
        return sysUserMapper.selectBatchIds(ids);
    }

    /**
     * param构建条件
     */
    @Override
    public List<SysUser> selectList(SysUser param) {
        Wrapper<SysUser> wrapper = getWrapper(param);
        return sysUserMapper.selectList(wrapper);
    }

    /**
     * 查询All
     *
     * @return List<SysUser>
     */
    @Override
    public List<SysUser> selectAll() {
        return sysUserMapper.selectAllInfo();
    }

    /**
     * 分页查询所有
     */
    @Override
    public Page<SysUser> selectPage(Integer currentNo, Integer pageSize) {
        if (currentNo <= 0) {
            currentNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        Page page = new Page<SysUser>(currentNo, pageSize, true);
        return sysUserMapper.selectPage(page, null);
    }

    /**
     * @param param 通过param构建条件查询多个SysUser
     * @return List<SysUser>
     */
    @Override
    public Page<SysUser> selectPage(Integer currentNo, Integer pageSize, SysUser param) {
        if (currentNo <= 0) {
            currentNo = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        Wrapper<SysUser> wrapper = getWrapper(param);
        return sysUserMapper.selectPage(new Page<>(currentNo, pageSize), wrapper);
    }


    /**
     * @param list 批量新增
     * @return Integer 受影响条数
     */
    @Override
    public Integer insertList(List<SysUser> list) {
        Integer integer = sysUserMapper.insertBatchSomeColumn(list);
        return integer;
    }

    /**
     * @param param 单个新增
     * @return boolean
     */
    @Override
    public boolean insert(SysUser param) {

        // 随机生成盐值
        String salt = RandomStringUtils.randomAlphanumeric(20);
        param.setEncryption(salt);
        String s = SHA256Util.sha256(param.getPassword(), param.getEncryption());
        param.setPassword(s);
        int i = sysUserMapper.insert(param);
        return i < 1 ? false : true;
    }

    /**
     * @param param 必须带有主键
     * @return boolean
     */
    @Override
    public boolean updateById(SysUser param) {
        int i = sysUserMapper.updateById(param);
        return i < 1 ? false : true;
    }


    /**
     * @param id 通过id删除
     * @return boolean
     */
    @Override
    public boolean deleteById(Long id) {
        int i = sysUserMapper.deleteById(id);
        return i < 1 ? false : true;
    }

    /**
     * @param ids 通过id集合批量删除
     * @return boolean
     */
    @Override
    public boolean deleteByIds(List<Long> ids) {
        int i = sysUserMapper.deleteBatchIds(ids);
        return i < 1 ? false : true;
    }


    /**
     * 用户表条件列表
     *
     * @param param 根据需要进行传值
     * @return
     */
    public Wrapper<SysUser> getWrapper(SysUser param) {

        return Wrappers.<SysUser>lambdaUpdate()
                // 主键
                .eq(param.getId() != null, SysUser::getId, param.getId())
                // 用户名
                .eq(!StringUtils.isEmpty(param.getName()), SysUser::getName, param.getName())
                // 账户名
                .eq(!StringUtils.isEmpty(param.getAccount()), SysUser::getAccount, param.getAccount())
                // 明文密码
                .eq(!StringUtils.isEmpty(param.getPassword()), SysUser::getPassword, param.getPassword())
                // 加密密码
                .eq(!StringUtils.isEmpty(param.getEncryption()), SysUser::getEncryption, param.getEncryption())
                // 创建时间
                .eq(param.getCreateDate() != null, SysUser::getCreateDate, param.getCreateDate())
                // 逻辑删除
                .eq(param.getIsDelete() != null, SysUser::getIsDelete, param.getIsDelete())
                ;
    }
}
