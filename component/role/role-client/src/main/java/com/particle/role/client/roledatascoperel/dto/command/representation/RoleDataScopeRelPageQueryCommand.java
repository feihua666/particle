package com.particle.role.client.roledatascoperel.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 角色数据范围关系 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Data
@Schema
public class RoleDataScopeRelPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "数据对象id")
    private Long dataObjectId;


    @Schema(description = "数据范围id")
    private Long dataScopeId;









}
