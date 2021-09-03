package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.DistributionScope;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.DistributionScopeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 配送区域 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/distributionScope")
@Api(tags = "配送区域")
public class DistributionScopeController {

    @Autowired
    private DistributionScopeService distributionScopeService;



    @ApiOperation(value = "配送区域id查询单个详情", response = DistributionScope.class,tags={" DistributionScopeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<DistributionScope> selectById(@PathVariable("id") Long id) {

        DistributionScope data = distributionScopeService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "配送区域实体条件查询多个", response = DistributionScope.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<DistributionScope> selectByEntity( @ApiParam(name = "DistributionScope对象",value = "传入JSON数据",required = false) @RequestBody DistributionScope param) {

        List<DistributionScope> data = distributionScopeService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "配送区域批量id查询", response = DistributionScope.class)
    @PostMapping(value = "/selectByIds")
    public  Result<DistributionScope> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<DistributionScope> data = distributionScopeService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "DistributionScope分类查询所有", response = DistributionScope.class)
    public  Result<DistributionScope> selectAll(){

        List<DistributionScope> data =  distributionScopeService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "配送区域分页查询所有", response = DistributionScope.class)
    public  Result<DistributionScope> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<DistributionScope> data = distributionScopeService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "配送区域分页条件查询所有", response = DistributionScope.class)
    public  Result<DistributionScope> selectAll(
        @ApiParam(name = "DistributionScope对象", value = "传入JSON数据", required = true) @RequestBody DistributionScope param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<DistributionScope> data = distributionScopeService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增配送区域",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "DistributionScope对象",value = "传入JSON数据",required = false) @RequestBody DistributionScope param) {

        boolean save = distributionScopeService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "配送区域修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "DistributionScope对象",value = "传入JSON数据",required = false) @RequestBody DistributionScope param) {

        boolean flag=distributionScopeService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "配送区域删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=distributionScopeService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除DistributionScope失败", "");
        }
        return new Result(true, StatusCode.OK, "删除DistributionScope成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件DistributionScope失败,请传入数据", "");
        }
        boolean flag=distributionScopeService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件DistributionScope删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

