package com.phj.common.shiro;


import com.phj.common.util.ShiroUtils;
import com.phj.mapper.SysRolePermissionRelationMapper;
import com.phj.mapper.SysUserRoleRelationMapper;
import com.phj.pojo.SysPermission;
import com.phj.pojo.SysRole;
import com.phj.pojo.SysUser;
import com.phj.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: spring-boot-shiro
 * @description:
 * @author: Mr.Pan
 * @create: 2021-07-21 17:13
 **/
public class UserRealm  extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleRelationMapper sysUserRoleRelationMapper;

    @Autowired
    private SysRolePermissionRelationMapper sysRolePermissionRelationMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        //执行登录
        String account = userToken.getUsername();

        SysUser user = new SysUser();
        user.setAccount(account);
        SysUser sysUser = sysUserService.selectOne(user);

        if (sysUser==null){
            //用户名不存在
            return null; //shiro底层就会抛出 UnknownAccountException
        }
        //判断账号是否被冻结
        if (sysUser.getIsDelete().equals(2)){
            throw new LockedAccountException();
        }
        //进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser,                                  //用户名
                sysUser.getPassword(),                    //密码
                ByteSource.Util.bytes(sysUser.getEncryption()), //设置盐值
                getName()
        );
        //验证成功开始踢人(清除缓存和Session)
        ShiroUtils.deleteCache(account,true);

        //2. 验证密码,我们可以使用一个AuthenticationInfo实现类 SimpleAuthenticationInfo
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //这里可以进行授权和处理
        Set<String> rolesSet = new HashSet<>();
        Set<String> permsSet = new HashSet<>();
        // SecurityUtils.getSubject().getPrincipal();效果一样
        SysUser user  = (SysUser)principalCollection.getPrimaryPrincipal();

        List<SysRole> sysRoles = sysUserRoleRelationMapper.selectByUid(user.getId());
        List<Long> rids = new ArrayList<>();
        for (SysRole sysRole : sysRoles) {
            rids.add(sysRole.getId());
        }

        List<SysPermission> sysPermissions = sysRolePermissionRelationMapper.selectByRids(rids);

        for(SysRole role:sysRoles){
            //添加角色
            rolesSet.add(role.getName());
        }
        for(SysPermission p:sysPermissions){
            //添加资源的授权字符串
            permsSet.add(p.getCode());
        }
        //将查到的权限和角色分别传入authorizationInfo中
        info.setStringPermissions(permsSet);
        info.setRoles(rolesSet);

        return info;
    }
}
