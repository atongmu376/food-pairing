package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.DistributionPath;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.DistributionPathService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 配送路线表 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/distributionPath")
@Api(tags = "配送路线表")
public class DistributionPathController {

    @Autowired
    private DistributionPathService distributionPathService;



    @ApiOperation(value = "配送路线表id查询单个详情", response = DistributionPath.class,tags={" DistributionPathController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<DistributionPath> selectById(@PathVariable("id") Long id) {

        DistributionPath data = distributionPathService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "配送路线表实体条件查询多个", response = DistributionPath.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<DistributionPath> selectByEntity( @ApiParam(name = "DistributionPath对象",value = "传入JSON数据",required = false) @RequestBody DistributionPath param) {

        List<DistributionPath> data = distributionPathService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "配送路线表批量id查询", response = DistributionPath.class)
    @PostMapping(value = "/selectByIds")
    public  Result<DistributionPath> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<DistributionPath> data = distributionPathService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "DistributionPath分类查询所有", response = DistributionPath.class)
    public  Result<DistributionPath> selectAll(){

        List<DistributionPath> data =  distributionPathService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "配送路线表分页查询所有", response = DistributionPath.class)
    public  Result<DistributionPath> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<DistributionPath> data = distributionPathService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "配送路线表分页条件查询所有", response = DistributionPath.class)
    public  Result<DistributionPath> selectAll(
        @ApiParam(name = "DistributionPath对象", value = "传入JSON数据", required = true) @RequestBody DistributionPath param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<DistributionPath> data = distributionPathService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增配送路线表",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "DistributionPath对象",value = "传入JSON数据",required = false) @RequestBody DistributionPath param) {

        boolean save = distributionPathService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "配送路线表修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "DistributionPath对象",value = "传入JSON数据",required = false) @RequestBody DistributionPath param) {

        boolean flag=distributionPathService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "配送路线表删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=distributionPathService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除DistributionPath失败", "");
        }
        return new Result(true, StatusCode.OK, "删除DistributionPath成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件DistributionPath失败,请传入数据", "");
        }
        boolean flag=distributionPathService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件DistributionPath删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

