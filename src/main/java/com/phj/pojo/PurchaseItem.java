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

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 采购单明细
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
@TableName("tb_purchase_item")
@ApiModel(value="PurchaseItem对象", description="采购单明细")
public class PurchaseItem extends Model<PurchaseItem> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;


    @ApiModelProperty(value = "食材id")
    @TableField("food_id")
    @NotNull(message = "食材id不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long foodId;


    @ApiModelProperty(value = "订单号")
    @TableField("purchase_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long purchaseId;


    @ApiModelProperty(value = "进货价")
    @TableField("purchase_price")
    @NotNull(message = "进货价不能为空")
    private BigDecimal purchasePrice;


    @ApiModelProperty(value = "进货数量")
    @TableField("num")
    @NotNull(message = "进货数量不能为空")
    private Integer num;

    @ApiModelProperty(value = "食材")
    @TableField(exist = false)
    private Food food;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
