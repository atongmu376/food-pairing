package com.phj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.pojo.MealRank;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.MealRankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 餐别类型维护 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/mealRank")
@Api(tags = "餐别类型维护")
public class MealRankController {

    @Autowired
    private MealRankService mealRankService;



    @ApiOperation(value = "餐别类型维护id查询单个详情", response = MealRank.class,tags={" MealRankController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<MealRank> selectById(@PathVariable("id") Long id) {

        MealRank data = mealRankService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "餐别类型维护实体条件查询多个", response = MealRank.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<MealRank> selectByEntity( @ApiParam(name = "MealRank对象",value = "传入JSON数据",required = false) @RequestBody MealRank param) {

        List<MealRank> data = mealRankService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "餐别类型维护批量id查询", response = MealRank.class)
    @PostMapping(value = "/selectByIds")
    public  Result<MealRank> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<MealRank> data = mealRankService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "MealRank分类查询所有", response = MealRank.class)
    public  Result<MealRank> selectAll(){

        List<MealRank> data =  mealRankService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @GetMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "餐别类型维护分页查询所有", response = MealRank.class)
    public  Result<MealRank> selectAll(
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {

        Page<MealRank> data = mealRankService.selectPage(currentNo,pageSize);
        if(data.getRecords().size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "餐别类型维护分页条件查询所有", response = MealRank.class)
    public  Result<MealRank> selectAll(
        @ApiParam(name = "MealRank对象", value = "传入JSON数据", required = true) @RequestBody MealRank param,
        @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
        @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        Page<MealRank> data = mealRankService.selectPage(currentNo,pageSize,param);
        if(data.getRecords().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @ApiOperation(value = "新增餐别类型维护",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "MealRank对象",value = "传入JSON数据",required = false) @RequestBody MealRank param) {

        boolean save = mealRankService.insert(param);
        if(!save){
            return new Result(false, StatusCode.ERROR, "新增失败", "");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }

    @ApiOperation(value = "餐别类型维护修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "MealRank对象",value = "传入JSON数据",required = false) @RequestBody MealRank param) {

        boolean flag=mealRankService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "餐别类型维护删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=mealRankService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除MealRank失败", "");
        }
        return new Result(true, StatusCode.OK, "删除MealRank成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件MealRank失败,请传入数据", "");
        }
        boolean flag=mealRankService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件MealRank删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

