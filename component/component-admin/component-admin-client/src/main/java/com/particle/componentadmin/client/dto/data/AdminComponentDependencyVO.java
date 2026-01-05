package com.particle.componentadmin.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransTableNameConstants;
/**
 * <p>
 * 组件依赖关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Data
@Schema
public class AdminComponentDependencyVO extends AbstractBaseIdVO {

    @Schema(description = "源组件id")
    private Long componentId;

    @Schema(description = "源组件英文名称")
    @TransBy(tableName = TransTableNameConstants.component_admin_component, byFieldName = "componentId", mapValueField = "code")
    private String componentCode;

    @Schema(description = "源组件中文名称")
    @TransBy(tableName = TransTableNameConstants.component_admin_component, byFieldName = "componentId", mapValueField = "name")
    private String componentName;

    @Schema(description = "依赖组件id")
    private Long dependComponentId;


    @Schema(description = "依赖组件英文名称")
    @TransBy(tableName = TransTableNameConstants.component_admin_component, byFieldName = "dependComponentId", mapValueField = "code")
    private String dependComponentCode;

    @Schema(description = "依赖组件中文名称")
    @TransBy(tableName = TransTableNameConstants.component_admin_component, byFieldName = "dependComponentId", mapValueField = "name")
    private String dependComponentName;

    @Schema(description = "是否必需")
    private Boolean isRequired;

    @Schema(description = "备注")
    private String remark;



}
