package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.pojo.Supplier;
import com.phj.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 供应商 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/supplier")
@Api(tags = "供应商")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;



    @ApiOperation(value = "供应商id查询单个详情", response = Supplier.class,tags={" SupplierController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<Supplier> selectById(@PathVariable("id") Long id) {

        Supplier data = supplierService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "供应商实体条件查询多个", response = Supplier.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<Supplier> selectByEntity( @ApiParam(name = "Supplier对象",value = "传入JSON数据",required = false) @RequestBody Supplier param) {

        List<Supplier> data = supplierService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "供应商批量id查询", response = Supplier.class)
    @PostMapping(value = "/selectByIds")
    public  Result<Supplier> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<Supplier> data = supplierService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "Supplier分类查询所有", response = Supplier.class)
    public  Result<Supplier> selectAll(){

        List<Supplier> data =  supplierService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "供应商分页查询所有", response = Supplier.class)
    public  Result<Supplier> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<Supplier> data = supplierService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "供应商分页条件查询所有", response = Supplier.class)
    public  Result<Supplier> selectAll(
        @ApiParam(name = "Supplier对象", value = "传入JSON数据", required = true) @RequestBody Supplier param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<Supplier> data = supplierService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增供应商",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "Supplier对象",value = "传入JSON数据",required = false) @RequestBody Supplier param) {

        boolean save = supplierService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "供应商修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "Supplier对象",value = "传入JSON数据",required = false) @RequestBody Supplier param) {

        boolean flag=supplierService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "供应商删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=supplierService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除Supplier失败", "");
        }
        return new Result(true, StatusCode.OK, "删除Supplier成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件Supplier失败,请传入数据", "");
        }
        boolean flag=supplierService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件Supplier删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

