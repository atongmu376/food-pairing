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
import java.time.LocalDate;

/**
 * <p>
 * 供应商
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
@TableName("tb_supplier")
@ApiModel(value="Supplier对象", description="供应商")
public class Supplier extends Model<Supplier> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;


    @ApiModelProperty(value = "供应商名称")
    @TableField("name")
    private String name;


    @ApiModelProperty(value = "手机号")
    @TableField("phone_num")
    private String phoneNum;


    @ApiModelProperty(value = "地址")
    @TableField("address")
    private String address;


    @ApiModelProperty(value = "资质图片")
    @TableField("certification_image")
    private String certificationImage;


    @ApiModelProperty(value = "到期时间")
    @TableField("expiration")
    private LocalDate expiration;


    @ApiModelProperty(value = "逻辑删除  (0代表未删除，1代表删除)")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
