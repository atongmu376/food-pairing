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

/**
 * <p>
 * 配送客户
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_distribution_client")
@ApiModel(value="DistributionClient对象", description="配送客户")
public class DistributionClient extends Model<DistributionClient> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;


    @ApiModelProperty(value = "客户名称")
    @TableField("name")
    private String name;


    @ApiModelProperty(value = "联系人名称")
    @TableField("phone_name")
    private String phoneName;


    @ApiModelProperty(value = "联系电话")
    @TableField("phone_num")
    private String phoneNum;


    @ApiModelProperty(value = "配送地址")
    @TableField("address")
    private String address;


    @ApiModelProperty(value = "0普通，1vip1推类")
    @TableField("client_type")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long clientType;


    @ApiModelProperty(value = "配送类型id")
    @TableField("type_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long typeId;


    @ApiModelProperty(value = "配送小组")
    @TableField("group_id")
    private String groupId;


    @ApiModelProperty(value = "配送路线")
    @TableField("path_id")
    private String pathId;


    @ApiModelProperty(value = "配送区域")
    @TableField("scope_id")
    private String scopeId;


    @ApiModelProperty(value = "逻辑删除(0代表未删除，1代表删除)")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;


    @ApiModelProperty(value = "配货分拣(0代表分拣，1不分拣)")
    @TableField("distributionSorting")
    private Integer distributionSorting;

    @ApiModelProperty(value = "配送分组实体类")
    @TableField(exist = false)
    private DistributionGroup groupData;

    @ApiModelProperty(value = "配送路线实体类")
    @TableField(exist = false)
    private DistributionPath pathData;

    @ApiModelProperty(value = "配送类型实体类")
    @TableField(exist = false)
    private DistributionType typeData;

    @ApiModelProperty(value = "配送区域实体类")
    @TableField(exist = false)
    private DistributionScope scopeData;

    @ApiModelProperty(value = "客户类型实体类")
    @TableField(exist = false)
    private ClientType clientTypeData;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
