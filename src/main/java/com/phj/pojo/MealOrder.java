package com.phj.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 排餐订单
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
@TableName("tb_meal_order")
@ApiModel(value="MealOrder对象", description="排餐订单")
public class MealOrder extends Model<MealOrder> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;



    @ApiModelProperty(value = "客户id")
    @TableField("client_id")
    @NotNull(message = "客户id为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long clientId;


    @ApiModelProperty(value = "餐标（每餐价格）")
    @TableField("meal_price")
    @NotNull(message = "餐标为空")
    private BigDecimal mealPrice;


    @ApiModelProperty(value = "用餐人数")
    @TableField("person_num")
    @NotNull(message = "用餐人数为空")
    private Integer personNum;


    @ApiModelProperty(value = "总金额")
    @TableField("total_price")
    private BigDecimal totalPrice;


    @ApiModelProperty(value = "餐别id")
    @TableField("meal_rank_id")
    @NotNull(message = "餐别id为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long mealRankId;


    @ApiModelProperty(value = "用餐时间")
    @TableField("meal_time")
    @NotNull(message = "用餐时间为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate mealTime;


    @ApiModelProperty(value = "配送标志（0代表未配送，1代表配送中）")
    @TableField("carriage_type")
    private Integer carriageType;


    @ApiModelProperty(value = "配送时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("deliveryTime")
    private LocalDate deliveryTime;


    @ApiModelProperty(value = "制单人")
    @TableField("sysUserId")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long sysUserId;


    @ApiModelProperty(value = "配货时间")
    @TableField("purchase_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate purchaseDate;


    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_date",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateDate;


    @ApiModelProperty(value = "制单时间")
    @TableField(value = "create_date",fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;

    @ApiModelProperty("订单项")
    @TableField(exist = false)
    private List<MealOrderItem> mealOrderItems;


    @ApiModelProperty("客户信息")
    @TableField(exist = false)
    private DistributionClient client;

    @ApiModelProperty("餐别信息")
    @TableField(exist = false)
    private MealRank mealRank;

    @ApiModelProperty("制单人信息")
    @TableField(exist = false)
    private SysUser user;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
