package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.pojo.SysPermission;
import com.phj.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/sysPermission")
@Api(tags = "权限表")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;



    @ApiOperation(value = "权限表id查询单个详情", response = SysPermission.class,tags={" SysPermissionController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<SysPermission> selectById(@PathVariable("id") Long id) {

        SysPermission data = sysPermissionService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "权限表实体条件查询多个", response = SysPermission.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<SysPermission> selectByEntity( @ApiParam(name = "SysPermission对象",value = "传入JSON数据",required = false) @RequestBody SysPermission param) {

        List<SysPermission> data = sysPermissionService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "权限表批量id查询", response = SysPermission.class)
    @PostMapping(value = "/selectByIds")
    public  Result<SysPermission> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<SysPermission> data = sysPermissionService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "SysPermission分类查询所有", response = SysPermission.class)
    public  Result<SysPermission> selectAll(){

        List<SysPermission> data =  sysPermissionService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "权限表分页查询所有", response = SysPermission.class)
    public  Result<SysPermission> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<SysPermission> data = sysPermissionService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "权限表分页条件查询所有", response = SysPermission.class)
    public  Result<SysPermission> selectAll(
        @ApiParam(name = "SysPermission对象", value = "传入JSON数据", required = true) @RequestBody SysPermission param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<SysPermission> data = sysPermissionService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增权限表",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "SysPermission对象",value = "传入JSON数据",required = false) @RequestBody SysPermission param) {

        boolean save = sysPermissionService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "权限表修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "SysPermission对象",value = "传入JSON数据",required = false) @RequestBody SysPermission param) {

        boolean flag=sysPermissionService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "权限表删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=sysPermissionService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除SysPermission失败", "");
        }
        return new Result(true, StatusCode.OK, "删除SysPermission成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件SysPermission失败,请传入数据", "");
        }
        boolean flag=sysPermissionService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件SysPermission删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

