package com.particle.role.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.Constants;
import com.particle.global.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 角色 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class RoleVO extends AbstractBaseIdVO {


    @ApiModelProperty("角色编码,模糊查询")
    private String code;

    @ApiModelProperty("角色名称,模糊查询")
    private String name;

    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty("禁用原因")
    private String disabledReason;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("父级")
    private Long parentId;

    @ApiModelProperty("父级名称")
    @TransBy(tableName = TransTableNameConstants.component_role, byFieldName = "parentId", mapValueField = "name")
    private String parentName;
}
