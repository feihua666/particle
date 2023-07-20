package com.particle.func.client.funcapplicationfuncrel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 功能应用功能关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Data
@Schema
public class FuncApplicationFuncRelCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "功能应用id 不能为空")
        @Schema(description = "功能应用id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long funcApplicationId;


    @NotNull(message = "功能id 不能为空")
        @Schema(description = "功能id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long funcId;









}
