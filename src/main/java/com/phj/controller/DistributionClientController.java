package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.phj.pojo.DistributionClient;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.DistributionClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 配送客户 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/distributionClient")
@Api(tags = "配送客户")
public class DistributionClientController {

    @Autowired
    private DistributionClientService distributionClientService;



    @ApiOperation(value = "配送客户id查询单个详情", response = DistributionClient.class,tags={" DistributionClientController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<DistributionClient> selectById(@PathVariable("id") Long id) {

        DistributionClient data = distributionClientService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "配送客户实体条件查询多个", response = DistributionClient.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<DistributionClient> selectByEntity( @ApiParam(name = "DistributionClient对象",value = "传入JSON数据",required = false) @RequestBody DistributionClient param) {

        List<DistributionClient> data = distributionClientService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "配送客户批量id查询", response = DistributionClient.class)
    @PostMapping(value = "/selectByIds")
    public  Result<DistributionClient> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<DistributionClient> data = distributionClientService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "DistributionClient分类查询所有", response = DistributionClient.class)
    public  Result<DistributionClient> selectAll(){

        List<DistributionClient> data =  distributionClientService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "配送客户分页查询所有", response = DistributionClient.class)
    public  Result<DistributionClient> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<DistributionClient> data = distributionClientService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "配送客户分页条件查询所有", response = DistributionClient.class)
    public  Result<DistributionClient> selectAll(
        @ApiParam(name = "DistributionClient对象", value = "传入JSON数据", required = true) @RequestBody DistributionClient param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        PageInfo<DistributionClient> data = distributionClientService.selectPage(currentNo,pageSize,param);
        if(data.getList().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增配送客户",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "DistributionClient对象",value = "传入JSON数据",required = false) @RequestBody DistributionClient param) {

        boolean save = distributionClientService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "配送客户修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "DistributionClient对象",value = "传入JSON数据",required = false) @RequestBody DistributionClient param) {

        boolean flag=distributionClientService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "配送客户删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=distributionClientService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除DistributionClient失败", "");
        }
        return new Result(true, StatusCode.OK, "删除DistributionClient成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件DistributionClient失败,请传入数据", "");
        }
        boolean flag=distributionClientService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件DistributionClient删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

