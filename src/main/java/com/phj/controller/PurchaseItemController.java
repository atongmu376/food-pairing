package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.PurchaseItem;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.PurchaseItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 采购单明细 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/purchaseItem")
@Api(tags = "采购单明细")
public class PurchaseItemController {

    @Autowired
    private PurchaseItemService purchaseItemService;



    @ApiOperation(value = "采购单明细id查询单个详情", response = PurchaseItem.class,tags={" PurchaseItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<PurchaseItem> selectById(@PathVariable("id") Long id) {

        PurchaseItem data = purchaseItemService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "采购单明细实体条件查询多个", response = PurchaseItem.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<PurchaseItem> selectByEntity( @ApiParam(name = "PurchaseItem对象",value = "传入JSON数据",required = false) @RequestBody PurchaseItem param) {

        List<PurchaseItem> data = purchaseItemService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "采购单明细批量id查询", response = PurchaseItem.class)
    @PostMapping(value = "/selectByIds")
    public  Result<PurchaseItem> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<PurchaseItem> data = purchaseItemService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "PurchaseItem分类查询所有", response = PurchaseItem.class)
    public  Result<PurchaseItem> selectAll(){

        List<PurchaseItem> data =  purchaseItemService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "采购单明细分页查询所有", response = PurchaseItem.class)
    public  Result<PurchaseItem> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<PurchaseItem> data = purchaseItemService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "采购单明细分页条件查询所有", response = PurchaseItem.class)
    public  Result<PurchaseItem> selectAll(
        @ApiParam(name = "PurchaseItem对象", value = "传入JSON数据", required = true) @RequestBody PurchaseItem param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<PurchaseItem> data = purchaseItemService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增采购单明细",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "PurchaseItem对象",value = "传入JSON数据",required = false) @RequestBody PurchaseItem param) {

        boolean save = purchaseItemService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "采购单明细修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "PurchaseItem对象",value = "传入JSON数据",required = false) @RequestBody PurchaseItem param) {

        boolean flag=purchaseItemService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "采购单明细删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=purchaseItemService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除PurchaseItem失败", "");
        }
        return new Result(true, StatusCode.OK, "删除PurchaseItem成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件PurchaseItem失败,请传入数据", "");
        }
        boolean flag=purchaseItemService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件PurchaseItem删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

