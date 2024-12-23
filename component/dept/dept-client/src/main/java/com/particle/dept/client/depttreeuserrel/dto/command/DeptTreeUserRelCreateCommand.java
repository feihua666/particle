package com.particle.dept.client.depttreeuserrel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 部门树用户关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Data
@Schema
public class DeptTreeUserRelCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "用户id 不能为空")
        @Schema(description = "用户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;


    @NotNull(message = "部门树id 不能为空")
        @Schema(description = "部门树id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long deptTreeId;









}
