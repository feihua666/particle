package com.particle.componentadmin.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 组件依赖关系 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Data
@Schema
public class AdminComponentDependencyQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "源组件id")
    private Long componentId;


    @Schema(description = "依赖组件id")
    private Long dependComponentId;


    @Schema(description = "是否必需")
    private Boolean isRequired;










}
