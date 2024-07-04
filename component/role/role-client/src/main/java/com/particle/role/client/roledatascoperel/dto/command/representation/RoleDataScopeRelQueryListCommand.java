package com.particle.role.client.roledatascoperel.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色数据范围关系 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Data
@Schema
public class RoleDataScopeRelQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "数据对象id")
    private Long dataObjectId;


    @Schema(description = "数据范围id")
    private Long dataScopeId;









}