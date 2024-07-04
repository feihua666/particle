package com.particle.role.client.roledatascoperel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色数据范围关系 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Data
@Schema
public class RoleDataScopeRelUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "角色id 不能为空")
        @Schema(description = "角色id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long roleId;

    @NotNull(message = "数据对象id 不能为空")
    @Schema(description = "数据对象id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataObjectId;


    @NotNull(message = "数据范围id 不能为空")
        @Schema(description = "数据范围id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataScopeId;









}