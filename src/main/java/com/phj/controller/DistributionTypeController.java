package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.DistributionType;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.DistributionTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 配送类型 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/distributionType")
@Api(tags = "配送类型")
public class DistributionTypeController {

    @Autowired
    private DistributionTypeService distributionTypeService;



    @ApiOperation(value = "配送类型id查询单个详情", response = DistributionType.class,tags={" DistributionTypeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<DistributionType> selectById(@PathVariable("id") Long id) {

        DistributionType data = distributionTypeService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "配送类型实体条件查询多个", response = DistributionType.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<DistributionType> selectByEntity( @ApiParam(name = "DistributionType对象",value = "传入JSON数据",required = false) @RequestBody DistributionType param) {

        List<DistributionType> data = distributionTypeService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "配送类型批量id查询", response = DistributionType.class)
    @PostMapping(value = "/selectByIds")
    public  Result<DistributionType> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<DistributionType> data = distributionTypeService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "DistributionType分类查询所有", response = DistributionType.class)
    public  Result<DistributionType> selectAll(){

        List<DistributionType> data =  distributionTypeService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "配送类型分页查询所有", response = DistributionType.class)
    public  Result<DistributionType> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<DistributionType> data = distributionTypeService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "配送类型分页条件查询所有", response = DistributionType.class)
    public  Result<DistributionType> selectAll(
        @ApiParam(name = "DistributionType对象", value = "传入JSON数据", required = true) @RequestBody DistributionType param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<DistributionType> data = distributionTypeService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增配送类型",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "DistributionType对象",value = "传入JSON数据",required = false) @RequestBody DistributionType param) {

        boolean save = distributionTypeService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "配送类型修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "DistributionType对象",value = "传入JSON数据",required = false) @RequestBody DistributionType param) {

        boolean flag=distributionTypeService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "配送类型删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=distributionTypeService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除DistributionType失败", "");
        }
        return new Result(true, StatusCode.OK, "删除DistributionType成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件DistributionType失败,请传入数据", "");
        }
        boolean flag=distributionTypeService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件DistributionType删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

