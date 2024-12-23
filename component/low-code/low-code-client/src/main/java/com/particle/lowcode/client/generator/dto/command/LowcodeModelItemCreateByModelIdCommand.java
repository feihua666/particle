package com.particle.lowcode.client.generator.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 低代码模型项目 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Data
@Schema
public class LowcodeModelItemCreateByModelIdCommand extends AbstractBaseCommand {


    @NotNull(message = "模型id不能为空")
    @Schema(description = "模型id")
    private Long lowcodeModelId;

    @NotNull(message = "数据源id不能为空")
    @Schema(description = "数据源id")
    private Long lowcodeDatasourceId;
}
