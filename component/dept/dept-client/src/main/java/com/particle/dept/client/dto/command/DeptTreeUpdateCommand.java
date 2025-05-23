package com.particle.dept.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 部门树 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Data
@Schema
public class DeptTreeUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "部门id 不能为空")
    @Schema(description = "部门id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long deptId;


    @NotNull(message = "部门树名称id 不能为空")
    @Schema(description = "部门树名称id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long deptTreeNameId;


    @Schema(description = "描述")
    private String remark;


    @Schema(description = "父级")
    private Long parentId;


}
