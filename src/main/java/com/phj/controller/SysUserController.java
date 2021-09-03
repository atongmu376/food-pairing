package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.common.config.annotation.JsonObject;
import com.phj.common.util.IpAddressUtil;
import com.phj.common.util.RedisUtil;
import com.phj.common.util.ShiroUtils;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.pojo.SysUser;
import com.phj.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/sysUser")
@Api(tags = "用户表")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public Result<SysUser> login(@JsonObject("code") Integer code,@JsonObject("user") SysUser user, HttpServletRequest request){
        String ipAddress = IpAddressUtil.getIpAddress(request);
        Integer o = (Integer) redisUtil.get(ipAddress);
        if (o == null || code==null) {
            return new Result(false, StatusCode.ERROR, "请输入验证码");
        }
        //验证码相等
        if (o.equals(code)) {
            redisUtil.del(ipAddress);
                //使用shiro，编写认证操作
                //1. 获取Subject
                Subject subject = SecurityUtils.getSubject();
                //是否已认证
                if (!subject.isAuthenticated()) {
                    //没认证创建令牌
                    UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
                    try {
                        subject.login(token); //登录成功！返回首页
                    } catch (UnknownAccountException e) { //用户名不存在
                        return new Result(false, StatusCode.ERROR, "用户名错误" );
                    } catch (IncorrectCredentialsException e) { //密码错误
                        return new Result(false, StatusCode.ERROR, "密码错误");
                    } catch (LockedAccountException e) {
                        return new Result(false, StatusCode.ERROR, "登录失败，该用户已被冻结");
                    } catch (AuthenticationException e) {
                        return new Result(false, StatusCode.ERROR, "该用户不存在");
                    } catch (Exception e) {
                        return new Result(false, StatusCode.ERROR, "未知异常");
                    }
                }
            SysUser u = (SysUser) subject.getPrincipals().getPrimaryPrincipal();
            u.setPassword("");
            u.setEncryption("");
            return new Result(false, StatusCode.OK, "登录成功", u);
        }
        return new Result(false, StatusCode.ERROR, "验证码错误");
    }


    @PostMapping("/logout")
    public Result logout(){
        ShiroUtils.logout();
        return new Result(true, StatusCode.OK, "退出成功");
    }


    @ApiOperation(value = "用户表id查询单个详情", response = SysUser.class, tags = {" SysUserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, example = "1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public Result<SysUser> selectById(@PathVariable("id") Long id) {

        SysUser data = sysUserService.selectById(id);
        if (data == null) {
            return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "用户表实体条件查询多个", response = SysUser.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<SysUser> selectByEntity( @ApiParam(name = "SysUser对象",value = "传入JSON数据",required = false) @RequestBody SysUser param) {

        List<SysUser> data = sysUserService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "用户表批量id查询", response = SysUser.class)
    @PostMapping(value = "/selectByIds")
    public  Result<SysUser> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<SysUser> data = sysUserService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "SysUser查询所有", response = SysUser.class)
    public  Result<SysUser> selectAll(){

        List<SysUser> data =  sysUserService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "用户表分页查询所有", response = SysUser.class)
    public  Result<SysUser> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<SysUser> data = sysUserService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "用户表分页条件查询所有", response = SysUser.class)
    public  Result<SysUser> selectAll(
        @ApiParam(name = "SysUser对象", value = "传入JSON数据", required = true) @RequestBody SysUser param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<SysUser> data = sysUserService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增用户表",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "SysUser对象",value = "传入JSON数据",required = false) @RequestBody SysUser param) {

        boolean save = sysUserService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "用户表修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "SysUser对象",value = "传入JSON数据",required = false) @RequestBody SysUser param) {

        boolean flag=sysUserService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }


    @ApiOperation(value = "用户表删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=sysUserService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除SysUser失败", "");
        }
        return new Result(true, StatusCode.OK, "删除SysUser成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件SysUser失败,请传入数据", "");
        }
        boolean flag=sysUserService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件SysUser删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

