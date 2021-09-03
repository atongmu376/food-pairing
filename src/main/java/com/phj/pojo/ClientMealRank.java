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
 * 客户餐别
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
@TableName("tb_client_meal_rank")
@ApiModel(value="ClientMealRank对象", description="客户餐别")
public class ClientMealRank extends Model<ClientMealRank> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;


    @ApiModelProperty(value = "客户id")
    @TableField("client_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long clientId;


    @ApiModelProperty(value = "餐别id")
    @TableField("meal_rank_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long mealRankId;


    @ApiModelProperty(value = "餐标（每餐价格）")
    @TableField("meal_price")
    private BigDecimal mealPrice;


    @ApiModelProperty(value = "用餐人数")
    @TableField("person_num")
    private Integer personNum;

    @ApiModelProperty(value = "餐别")
    @TableField(exist = false)
    private MealRank mealRank;

    @ApiModelProperty(value = "客户")
    @TableField(exist = false)
    private DistributionClient clients;



    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
