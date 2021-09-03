package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.FoodUnit;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.FoodUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 食材计量单位 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/foodUnit")
@Api(tags = "食材计量单位")
public class FoodUnitController {

    @Autowired
    private FoodUnitService foodUnitService;



    @ApiOperation(value = "食材计量单位id查询单个详情", response = FoodUnit.class,tags={" FoodUnitController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<FoodUnit> selectById(@PathVariable("id") Long id) {

        FoodUnit data = foodUnitService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "食材计量单位实体条件查询多个", response = FoodUnit.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<FoodUnit> selectByEntity( @ApiParam(name = "FoodUnit对象",value = "传入JSON数据",required = false) @RequestBody FoodUnit param) {

        List<FoodUnit> data = foodUnitService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "食材计量单位批量id查询", response = FoodUnit.class)
    @PostMapping(value = "/selectByIds")
    public  Result<FoodUnit> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<FoodUnit> data = foodUnitService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "FoodUnit分类查询所有", response = FoodUnit.class)
    public  Result<FoodUnit> selectAll(){

        List<FoodUnit> data =  foodUnitService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "食材计量单位分页查询所有", response = FoodUnit.class)
    public  Result<FoodUnit> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<FoodUnit> data = foodUnitService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "食材计量单位分页条件查询所有", response = FoodUnit.class)
    public  Result<FoodUnit> selectAll(
        @ApiParam(name = "FoodUnit对象", value = "传入JSON数据", required = true) @RequestBody FoodUnit param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<FoodUnit> data = foodUnitService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增食材计量单位",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "FoodUnit对象",value = "传入JSON数据",required = false) @RequestBody FoodUnit param) {

        boolean save = foodUnitService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "食材计量单位修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "FoodUnit对象",value = "传入JSON数据",required = false) @RequestBody FoodUnit param) {

        boolean flag=foodUnitService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "食材计量单位删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=foodUnitService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除FoodUnit失败", "");
        }
        return new Result(true, StatusCode.OK, "删除FoodUnit成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件FoodUnit失败,请传入数据", "");
        }
        boolean flag=foodUnitService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件FoodUnit删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

