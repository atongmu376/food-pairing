package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.phj.pojo.Food;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.FoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 食材 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/food")
@Api(tags = "食材")
public class FoodController {

    @Autowired
    private FoodService foodService;



    @ApiOperation(value = "食材id查询单个详情", response = Food.class,tags={" FoodController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<Food> selectById(@PathVariable("id") Long id) {

        Food data = foodService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "食材实体条件查询多个", response = Food.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<Food> selectByEntity( @ApiParam(name = "Food对象",value = "传入JSON数据",required = false) @RequestBody Food param) {

        List<Food> data = foodService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "食材批量id查询", response = Food.class)
    @PostMapping(value = "/selectByIds")
    public  Result<Food> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<Food> data = foodService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "Food查询所有", response = Food.class)
    public  Result<Food> selectAll(){

        List<Food> data =  foodService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "食材分页查询所有", response = Food.class)
    public  Result<Food> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<Food> data = foodService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "食材分页条件查询所有", response = Food.class)
    public  Result<Food> selectAll(
        @ApiParam(name = "Food对象", value = "传入JSON数据", required = true) @RequestBody Food param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        System.out.println(param);
        PageInfo<Food> data = foodService.selectPage(currentNo, pageSize, param);
        if(data.getList().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增食材",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "Food对象",value = "传入JSON数据",required = false) @RequestBody Food param) {

        boolean save = foodService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "食材修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "Food对象",value = "传入JSON数据",required = false) @RequestBody Food param) {

        System.out.println(param);
        boolean flag=foodService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "食材删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=foodService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除Food失败", "");
        }
        return new Result(true, StatusCode.OK, "删除Food成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件Food失败,请传入数据", "");
        }
        boolean flag=foodService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件Food删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

