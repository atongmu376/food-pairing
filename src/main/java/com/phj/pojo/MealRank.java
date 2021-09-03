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

/**
 * <p>
 * 餐别类型维护
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
@TableName("tb_meal_rank")
@ApiModel(value="MealRank对象", description="餐别类型维护")
public class MealRank extends Model<MealRank> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;


    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;


    @ApiModelProperty(value = "显示顺序")
    @TableField("seq")
    private Integer seq;


    @ApiModelProperty(value = "统计分类")
    @TableField("category_name")
    private String categoryName;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
