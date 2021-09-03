package com.phj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @program: food-pairing
 * @description: 订单搜索实体类
 * @author: Mr.Pan
 * @create: 2021-08-27 11:50
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealOrderSelect {

    //order id
    private Long id;
    //就餐开始时间
    private LocalDate mealStart;
    //就餐结束时间
    private LocalDate mealEnd;
    //配货开始日期
    private LocalDate purchaseDateStart;
    //配货结束
    private LocalDate purchaseDateEnd;
    //客户id
    private Long clientId;
    //餐别id
    private Long mealRankId;
    //制单人id
    private Long sysUserId;
    //路线id
    private Long pathId;

    //是否配送
    private Integer carriageType;

}
