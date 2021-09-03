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

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 采购单
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_purchase")
@ApiModel(value="Purchase对象", description="采购单")
public class Purchase extends Model<Purchase> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键 订单号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;


    @ApiModelProperty(value = "总金额")
    @TableField("total_money")
    private BigDecimal totalMoney;


    @ApiModelProperty(value = "支付状态")
    @TableField("pay_status")

    private Integer payStatus;


    @ApiModelProperty(value = "采购类型（0代表临时，1代表批量）")
    @TableField("type")
    private Integer type;


    @ApiModelProperty(value = "采购人id")
    @TableField("worker_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long workerId;


    @ApiModelProperty(value = "供应商id")
    @TableField("supplier_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long supplierId;


    @ApiModelProperty(value = "备注")
    @TableField("comment")
    private String comment;


    @ApiModelProperty(value = "采购日期")
    @TableField("purchase_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate purchaseDate;


    @ApiModelProperty(value = "配货开始日期")
    @TableField("distribution_start")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate distributionStart;


    @ApiModelProperty(value = "配货结束时间")
    @TableField("distribution_end")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate distributionEnd;


    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date",fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;


    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "update_date",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateDate;


    @ApiModelProperty(value = "逻辑删除(0代表未删除，1代表删除)")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty(value = "订单项")
    @TableField(exist = false)
    private List<PurchaseItem> purchaseItems;

    @ApiModelProperty(value = "供应商")
    @TableField(exist = false)
    private Supplier supplier;

    @ApiModelProperty(value = "采购人")
    @TableField(exist = false)
    private SysUser sysUser;


    //查询条件 采购日期范围
    @TableField(exist = false)
    private LocalDate purchaseDateStart;
    @TableField(exist = false)
    private LocalDate purchaseDateEnd;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
