package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.ClientType;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.ClientTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 客户类型 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/clientType")
@Api(tags = "客户类型")
public class ClientTypeController {

    @Autowired
    private ClientTypeService clientTypeService;



    @ApiOperation(value = "客户类型id查询单个详情", response = ClientType.class,tags={" ClientTypeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<ClientType> selectById(@PathVariable("id") Long id) {

        ClientType data = clientTypeService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "客户类型实体条件查询多个", response = ClientType.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<ClientType> selectByEntity( @ApiParam(name = "ClientType对象",value = "传入JSON数据",required = false) @RequestBody ClientType param) {

        List<ClientType> data = clientTypeService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "客户类型批量id查询", response = ClientType.class)
    @PostMapping(value = "/selectByIds")
    public  Result<ClientType> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<ClientType> data = clientTypeService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "ClientType分类查询所有", response = ClientType.class)
    public  Result<ClientType> selectAll(){

        List<ClientType> data =  clientTypeService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "客户类型分页查询所有", response = ClientType.class)
    public  Result<ClientType> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<ClientType> data = clientTypeService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "客户类型分页条件查询所有", response = ClientType.class)
    public  Result<ClientType> selectAll(
        @ApiParam(name = "ClientType对象", value = "传入JSON数据", required = true) @RequestBody ClientType param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<ClientType> data = clientTypeService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增客户类型",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "ClientType对象",value = "传入JSON数据",required = false) @RequestBody ClientType param) {

        boolean save = clientTypeService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "客户类型修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "ClientType对象",value = "传入JSON数据",required = false) @RequestBody ClientType param) {

        boolean flag=clientTypeService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "客户类型删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=clientTypeService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除ClientType失败", "");
        }
        return new Result(true, StatusCode.OK, "删除ClientType成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件ClientType失败,请传入数据", "");
        }
        boolean flag=clientTypeService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件ClientType删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

