package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.FoodCategory;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.FoodCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 食材分类 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/foodCategory")
@Api(tags = "食材分类")
public class FoodCategoryController {

    @Autowired
    private FoodCategoryService foodCategoryService;



    @ApiOperation(value = "食材分类id查询单个详情", response = FoodCategory.class,tags={" FoodCategoryController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<FoodCategory> selectById(@PathVariable("id") Long id) {

        FoodCategory data = foodCategoryService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "食材分类实体条件查询多个", response = FoodCategory.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<FoodCategory> selectByEntity( @ApiParam(name = "FoodCategory对象",value = "传入JSON数据",required = false) @RequestBody FoodCategory param) {

        List<FoodCategory> data = foodCategoryService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "食材分类批量id查询", response = FoodCategory.class)
    @PostMapping(value = "/selectByIds")
    public  Result<FoodCategory> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<FoodCategory> data = foodCategoryService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "FoodCategory分类查询所有", response = FoodCategory.class)
    public  Result<FoodCategory> selectAll(){

        List<FoodCategory> data =  foodCategoryService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "食材分类分页查询所有", response = FoodCategory.class)
    public  Result<FoodCategory> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<FoodCategory> data = foodCategoryService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "食材分类分页条件查询所有", response = FoodCategory.class)
    public  Result<FoodCategory> selectAll(
        @ApiParam(name = "FoodCategory对象", value = "传入JSON数据", required = true) @RequestBody FoodCategory param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<FoodCategory> data = foodCategoryService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增食材分类",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "FoodCategory对象",value = "传入JSON数据",required = false) @RequestBody FoodCategory param) {

        boolean save = foodCategoryService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "食材分类修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "FoodCategory对象",value = "传入JSON数据",required = false) @RequestBody FoodCategory param) {

        boolean flag=foodCategoryService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "食材分类删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=foodCategoryService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除FoodCategory失败", "");
        }
        return new Result(true, StatusCode.OK, "删除FoodCategory成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件FoodCategory失败,请传入数据", "");
        }
        boolean flag=foodCategoryService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件FoodCategory删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

