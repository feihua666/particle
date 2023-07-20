package com.particle.role.client.rolefuncrel.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 角色菜单功能关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class RoleFuncRelVO extends AbstractBaseIdVO {


    @Schema(description = "角色id")
    private Long roleId;

    @TransBy(tableName = TransTableNameConstants.component_role, byFieldName = "roleId", mapValueField = "name")
    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "功能id")
    private Long funcId;

    @TransBy(type = TransConstants.TRANS_FUNC_BY_ID,byFieldName = "funcId",mapValueField = "name")
    @Schema(description = "功能菜单名称")
    private String funcName;
}
