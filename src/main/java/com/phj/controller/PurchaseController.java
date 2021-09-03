package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.phj.pojo.Purchase;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 采购单 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/purchase")
@Api(tags = "采购单")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;



    @ApiOperation(value = "采购单id查询单个详情", response = Purchase.class,tags={" PurchaseController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<Purchase> selectById(@PathVariable("id") Long id) {

        Purchase data = purchaseService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "采购单实体条件查询多个", response = Purchase.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<Purchase> selectByEntity( @ApiParam(name = "Purchase对象",value = "传入JSON数据",required = false) @RequestBody Purchase param) {

        List<Purchase> data = purchaseService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "采购单批量id查询", response = Purchase.class)
    @PostMapping(value = "/selectByIds")
    public  Result<Purchase> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<Purchase> data = purchaseService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "Purchase分类查询所有", response = Purchase.class)
    public  Result<Purchase> selectAll(){

        List<Purchase> data =  purchaseService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "采购单分页查询所有", response = Purchase.class)
    public  Result<Purchase> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<Purchase> data = purchaseService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "采购单分页条件查询所有", response = Purchase.class)
    public  Result<Purchase> selectAll(
        @ApiParam(name = "Purchase对象", value = "传入JSON数据", required = true) @RequestBody Purchase param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        PageInfo<Purchase> data = purchaseService.selectPage(currentNo,pageSize,param);
        if(data.getList().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增采购单",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "Purchase对象",value = "传入JSON数据",required = false) @RequestBody Purchase param) {

        boolean save = purchaseService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "采购单修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "Purchase对象",value = "传入JSON数据",required = false) @RequestBody Purchase param) {

        boolean flag=purchaseService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "采购单删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=purchaseService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除Purchase失败", "");
        }
        return new Result(true, StatusCode.OK, "删除Purchase成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件Purchase失败,请传入数据", "");
        }
        boolean flag=purchaseService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件Purchase删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

