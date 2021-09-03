package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.DistributionGroup;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.DistributionGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 配送小组 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/distributionGroup")
@Api(tags = "配送小组")
public class DistributionGroupController {

    @Autowired
    private DistributionGroupService distributionGroupService;



    @ApiOperation(value = "配送小组id查询单个详情", response = DistributionGroup.class,tags={" DistributionGroupController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<DistributionGroup> selectById(@PathVariable("id") Long id) {

        DistributionGroup data = distributionGroupService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "配送小组实体条件查询多个", response = DistributionGroup.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<DistributionGroup> selectByEntity( @ApiParam(name = "DistributionGroup对象",value = "传入JSON数据",required = false) @RequestBody DistributionGroup param) {

        List<DistributionGroup> data = distributionGroupService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "配送小组批量id查询", response = DistributionGroup.class)
    @PostMapping(value = "/selectByIds")
    public  Result<DistributionGroup> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<DistributionGroup> data = distributionGroupService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "DistributionGroup分类查询所有", response = DistributionGroup.class)
    public  Result<DistributionGroup> selectAll(){

        List<DistributionGroup> data =  distributionGroupService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "配送小组分页查询所有", response = DistributionGroup.class)
    public  Result<DistributionGroup> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<DistributionGroup> data = distributionGroupService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "配送小组分页条件查询所有", response = DistributionGroup.class)
    public  Result<DistributionGroup> selectAll(
        @ApiParam(name = "DistributionGroup对象", value = "传入JSON数据", required = true) @RequestBody DistributionGroup param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<DistributionGroup> data = distributionGroupService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增配送小组",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "DistributionGroup对象",value = "传入JSON数据",required = false) @RequestBody DistributionGroup param) {

        boolean save = distributionGroupService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "配送小组修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "DistributionGroup对象",value = "传入JSON数据",required = false) @RequestBody DistributionGroup param) {

        boolean flag=distributionGroupService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "配送小组删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=distributionGroupService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除DistributionGroup失败", "");
        }
        return new Result(true, StatusCode.OK, "删除DistributionGroup成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件DistributionGroup失败,请传入数据", "");
        }
        boolean flag=distributionGroupService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件DistributionGroup删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

