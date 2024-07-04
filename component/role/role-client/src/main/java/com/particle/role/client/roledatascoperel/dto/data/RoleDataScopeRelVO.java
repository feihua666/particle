package com.particle.role.client.roledatascoperel.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 角色数据范围关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Data
@Schema
public class RoleDataScopeRelVO extends AbstractBaseIdVO {

    @Schema(description = "角色id")
    private Long roleId;


    @TransBy(tableName = TransTableNameConstants.component_role, byFieldName = "roleId", mapValueField = "name")
    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "数据对象id")
    private Long dataObjectId;

    @TransBy(type = TransConstants.TRANS_DATA_OBJECT_BY_ID,byFieldName = "dataObjectId",mapValueField = "name")
    @Schema(description = "数据对象名称")
    private String dataObjectName;
    
    @Schema(description = "数据范围id")
    private Long dataScopeId;

    @TransBy(type = TransConstants.TRANS_DATA_SCOPE_BY_ID,byFieldName = "dataScopeId",mapValueField = "name")
    @Schema(description = "数据范围名称")
    private String dataScopeName;

}