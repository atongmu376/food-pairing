package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.pojo.SysRolePermissionRelation;
import com.phj.service.SysRolePermissionRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 角色权限关系表 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/sysRolePermissionRelation")
@Api(tags = "角色权限关系表")
public class SysRolePermissionRelationController {

    @Autowired
    private SysRolePermissionRelationService sysRolePermissionRelationService;



    @ApiOperation(value = "角色权限关系表id查询单个详情", response = SysRolePermissionRelation.class,tags={" SysRolePermissionRelationController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<SysRolePermissionRelation> selectById(@PathVariable("id") Long id) {

        SysRolePermissionRelation data = sysRolePermissionRelationService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "角色权限关系表实体条件查询多个", response = SysRolePermissionRelation.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<SysRolePermissionRelation> selectByEntity( @ApiParam(name = "SysRolePermissionRelation对象",value = "传入JSON数据",required = false) @RequestBody SysRolePermissionRelation param) {

        List<SysRolePermissionRelation> data = sysRolePermissionRelationService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "角色权限关系表批量id查询", response = SysRolePermissionRelation.class)
    @PostMapping(value = "/selectByIds")
    public  Result<SysRolePermissionRelation> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<SysRolePermissionRelation> data = sysRolePermissionRelationService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "SysRolePermissionRelation分类查询所有", response = SysRolePermissionRelation.class)
    public  Result<SysRolePermissionRelation> selectAll(){

        List<SysRolePermissionRelation> data =  sysRolePermissionRelationService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "角色权限关系表分页查询所有", response = SysRolePermissionRelation.class)
    public  Result<SysRolePermissionRelation> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<SysRolePermissionRelation> data = sysRolePermissionRelationService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "角色权限关系表分页条件查询所有", response = SysRolePermissionRelation.class)
    public  Result<SysRolePermissionRelation> selectAll(
        @ApiParam(name = "SysRolePermissionRelation对象", value = "传入JSON数据", required = true) @RequestBody SysRolePermissionRelation param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<SysRolePermissionRelation> data = sysRolePermissionRelationService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增角色权限关系表",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "SysRolePermissionRelation对象",value = "传入JSON数据",required = false) @RequestBody SysRolePermissionRelation param) {

        boolean save = sysRolePermissionRelationService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "角色权限关系表修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "SysRolePermissionRelation对象",value = "传入JSON数据",required = false) @RequestBody SysRolePermissionRelation param) {

        boolean flag=sysRolePermissionRelationService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "角色权限关系表删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=sysRolePermissionRelationService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除SysRolePermissionRelation失败", "");
        }
        return new Result(true, StatusCode.OK, "删除SysRolePermissionRelation成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件SysRolePermissionRelation失败,请传入数据", "");
        }
        boolean flag=sysRolePermissionRelationService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件SysRolePermissionRelation删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

