package com.phj.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
 * 食材
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
@TableName("tb_food")
@ApiModel(value="Food对象", description="食材")
public class Food extends Model<Food> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;


    @ApiModelProperty(value = "食物名称")
    @TableField("name")
    private String name;


    @ApiModelProperty(value = "分类id")
    @TableField("category_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long categoryId;


    @ApiModelProperty(value = "规格")
    @TableField("specification")
    private String specification;


    @ApiModelProperty(value = "品牌")
    @TableField("brand")
    private String brand;


    @ApiModelProperty(value = "分配单位（克）")
    @TableField("unit")
    private Integer unit;

    @ApiModelProperty(value = "单位")
    @TableField("tb_unit_id")
    private Long unitId;

    @ApiModelProperty(value = "图片")
    @TableField("image")
    private String image;


    @ApiModelProperty(value = "进货价格")
    @TableField("market_price")
    private BigDecimal marketPrice;


    @ApiModelProperty(value = "销售价格")
    @TableField("purchase_price")
    private BigDecimal purchasePrice;


    @ApiModelProperty(value = "库存")
    @TableField("stock")
    private Integer stock;


    @ApiModelProperty(value = "乐观锁")
    @TableField("update_version")
    @Version
    private Integer updateVersion;


    @ApiModelProperty(value = "逻辑删除(0代表未删除，1代表删除)")
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;


    @ApiModelProperty(value = "单位实体")
    @TableField(exist = false)
    private FoodUnit foodUnit;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
