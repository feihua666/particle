package com.particle.dept.client.deptuserrel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 部门用户关系 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Data
@Schema
public class DeptUserRelUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "用户id 不能为空")
        @Schema(description = "用户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;


    @NotNull(message = "部门id 不能为空")
        @Schema(description = "部门id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long deptId;









}
