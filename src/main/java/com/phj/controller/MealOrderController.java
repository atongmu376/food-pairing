package com.phj.controller;

import com.github.pagehelper.PageInfo;
import com.phj.dto.MealOrderSelect;
import com.phj.pojo.MealOrder;
import com.phj.pojo.Result;
import com.phj.pojo.StatusCode;
import com.phj.service.MealOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 排餐订单 前端控制器
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@CrossOrigin(originPatterns = "*",allowCredentials="true",allowedHeaders = "*",methods = {})
@RestController
@RequestMapping("/mealOrder")
@Api(tags = "排餐订单")
public class MealOrderController {

    @Autowired
    private MealOrderService mealOrderService;



    @ApiOperation(value = "排餐订单id查询单个详情", response = MealOrder.class,tags={" MealOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @GetMapping(value = "/select/{id}")
    public  Result<MealOrder> selectById(@PathVariable("id") Long id) {

        MealOrder data = mealOrderService.selectById(id);
        if(data==null){
           return new Result(false, StatusCode.ERROR, "查询失败", data);
        }
        return new Result(true, StatusCode.OK, "查询成功", data);
    }

    @ApiOperation(value = "排餐订单实体条件查询多个", response = MealOrder.class)
    @PostMapping(value = "/selectByEntity")
    public  Result<MealOrder> selectByEntity( @ApiParam(name = "MealOrder对象",value = "传入JSON数据",required = false) @RequestBody MealOrder param) {

        List<MealOrder> data = mealOrderService.selectList(param);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "条件查询失败", data);
        }
        return new Result(true, StatusCode.OK, "条件查询成功", data);
    }

    @ApiOperation(value = "排餐订单批量id查询", response = MealOrder.class)
    @PostMapping(value = "/selectByIds")
    public  Result<MealOrder> selectByIds(@ApiParam(name = "id",value = "传入JSON数据",required = false)@RequestBody List<Long> ids) {

        List<MealOrder> data = mealOrderService.selectByIds(ids);
        if(data==null){
        return new Result(false, StatusCode.ERROR, "批量查询失败", data);
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }

    @PostMapping(value = "/selectAll")
    @ApiOperation(value = "MealOrder分类查询所有", response = MealOrder.class)
    public  Result<MealOrder> selectAll(){

        List<MealOrder> data =  mealOrderService.selectAll();
        if(data.size()<=0){
            return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", data);
    }


    @PostMapping(value = "/selectAll/{currentNo}/{pageSize}")
    @ApiOperation(value = "排餐订单分页条件查询所有", response = MealOrder.class)
    public  Result<MealOrder> selectAll(
            @ApiParam(name = "MealOrder对象", value = "传入JSON数据", required = true) @RequestBody MealOrderSelect select,
            @ApiParam(name = "currentNo", value = "当前页码") @PathVariable("currentNo") Integer currentNo,
            @ApiParam(name = "pageSize", value = "当前页显示条数") @PathVariable("pageSize") Integer pageSize)
    {
        PageInfo<MealOrder> mealOrderPageInfo = mealOrderService.selectPage(currentNo, pageSize, select);
        if(mealOrderPageInfo.getList().size()<=0){
             return new Result(false, StatusCode.OK, "没有数据");
        }
        return new Result(true, StatusCode.OK, "批量查询成功", mealOrderPageInfo);
    }

    @ApiOperation(value = "新增排餐订单",response = Result.class)
    @PostMapping(value = "/add")
    public Result add( @ApiParam(name = "MealOrder对象",value = "传入JSON数据",required = false) @Validated @RequestBody List<MealOrder> param) {


        Integer integer = mealOrderService.insertList(param);
        boolean save = false;
        if(integer<0){
            return new Result(false, StatusCode.ERROR, "新增失败");
        }
        return new Result(true, StatusCode.OK, "新增成功", save);
    }


    @ApiOperation(value = "配送", response = MealOrder.class)
    @PostMapping(value = "/carriage/{id}")
    public  Result<MealOrder> carriage(@PathVariable("id") Long id) {

        boolean flag = mealOrderService.carriage(id);
        if(flag){
            return new Result(false, StatusCode.ERROR, "配送状态修改失败");
        }
        return new Result(true, StatusCode.OK, "成功配送");
    }

    @ApiOperation(value = "排餐订单修改")
    @PutMapping(value = "/update")
    public  Result modify(@ApiParam(name = "MealOrder对象",value = "传入JSON数据",required = false) @RequestBody MealOrder param) {

        boolean flag=mealOrderService.updateById(param);
        if(!flag){
        return new Result(false, StatusCode.ERROR, "修改失败", "");
        }
        return new Result(true, StatusCode.OK, "修改成功", flag);
    }

    @ApiOperation(value = "排餐订单删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true,example="1", dataType = "Long")
    @DeleteMapping(value = "/delete/{id}")
    public  Result remove(@PathVariable Long id) {
        boolean flag=mealOrderService.deleteById(id);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "删除MealOrder失败", "");
        }
        return new Result(true, StatusCode.OK, "删除MealOrder成功", flag);
    }

    @ApiOperation(value = "多个id删除")
    @DeleteMapping(value = "/delete")
    public  Result removes(@ApiParam(name = "id集合",value = "传入JSON数据",required = false) @RequestBody List<Long> ids) {
        if(ids==null || ids.size()<=0){
            return new Result(false, StatusCode.ERROR, "条件MealOrder失败,请传入数据", "");
        }
        boolean flag=mealOrderService.deleteByIds(ids);
        if(!flag){
            return new Result(false, StatusCode.ERROR, "条件MealOrder删除失败", "");
        }
        return new Result(true, StatusCode.OK, "条件删除成功", flag);
    }

}

