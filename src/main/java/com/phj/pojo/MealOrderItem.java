package com.phj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 排餐订单明细
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_meal_order_item")
@ApiModel(value="MealOrderItem对象", description="排餐订单明细")
public class MealOrderItem extends Model<MealOrderItem> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;


    @ApiModelProperty(value = "排餐订单号")
    @TableField("meal_plan_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long mealPlanId;


    @ApiModelProperty(value = "食材id")
    @TableField("food_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long foodId;


    @ApiModelProperty(value = "销售价格")
    @TableField("market_price")
    private BigDecimal marketPrice;




    @ApiModelProperty(value = "总量")
    @TableField("total_quantity")
    private Integer totalQuantity;

    @ApiModelProperty(value = "食材")
    @TableField(exist = false)
    private Food food;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
