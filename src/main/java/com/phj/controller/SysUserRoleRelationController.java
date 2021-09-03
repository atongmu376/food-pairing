package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.pojo.SysUserRoleRelation;
import com.phj.service.SysUserRoleRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 用户与角色关系表 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/sysUserRoleRelation")
@Api(tags = "用户与角色关系表")
public class SysUserRoleRelationController {

    @Autowired
    private SysUserRoleRelationService sysUserRoleRelationService;



    @ApiOperation(value = "用户与角色关系表id查询单个详情", response = SysUserRoleRelation.class,tags={" SysUserRoleRelationController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<SysUserRoleRelation> selectById(@PathVariable("id") Long id) {

        SysUserRoleRelation data = sysUserRoleRelationService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "用户与角色关系表实体条件查询多个", response = SysUserRoleRelation.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<SysUserRoleRelation> selectByEntity( @ApiParam(name = "SysUserRoleRelation对象",value = "传入JSON数据",required = false) @RequestBody SysUserRoleRelation param) {

        List<SysUserRoleRelation> data = sysUserRoleRelationService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "用户与角色关系表批量id查询", response = SysUserRoleRelation.class)
    @PostMapping(value = "/selectByIds")
    public  Result<SysUserRoleRelation> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<SysUserRoleRelation> data = sysUserRoleRelationService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "SysUserRoleRelation分类查询所有", response = SysUserRoleRelation.class)
    public  Result<SysUserRoleRelation> selectAll(){

        List<SysUserRoleRelation> data =  sysUserRoleRelationService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "用户与角色关系表分页查询所有", response = SysUserRoleRelation.class)
    public  Result<SysUserRoleRelation> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<SysUserRoleRelation> data = sysUserRoleRelationService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "用户与角色关系表分页条件查询所有", response = SysUserRoleRelation.class)
    public  Result<SysUserRoleRelation> selectAll(
        @ApiParam(name = "SysUserRoleRelation对象", value = "传入JSON数据", required = true) @RequestBody SysUserRoleRelation param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<SysUserRoleRelation> data = sysUserRoleRelationService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增用户与角色关系表",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "SysUserRoleRelation对象",value = "传入JSON数据",required = false) @RequestBody SysUserRoleRelation param) {

        boolean save = sysUserRoleRelationService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "用户与角色关系表修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "SysUserRoleRelation对象",value = "传入JSON数据",required = false) @RequestBody SysUserRoleRelation param) {

        boolean flag=sysUserRoleRelationService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "用户与角色关系表删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=sysUserRoleRelationService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除SysUserRoleRelation失败", "");
        }
        return new Result(true, StatusCode.OK, "删除SysUserRoleRelation成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件SysUserRoleRelation失败,请传入数据", "");
        }
        boolean flag=sysUserRoleRelationService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件SysUserRoleRelation删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

