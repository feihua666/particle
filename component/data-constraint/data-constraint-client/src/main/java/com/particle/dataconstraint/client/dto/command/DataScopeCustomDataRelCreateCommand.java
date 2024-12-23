package com.particle.dataconstraint.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 数据范围自定义数据关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Data
@Schema
public class DataScopeCustomDataRelCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "数据范围id 不能为空")
        @Schema(description = "数据范围id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataScopeId;


    @NotNull(message = "自定义数据id 不能为空")
        @Schema(description = "自定义数据id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataId;









}
