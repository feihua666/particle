package com.particle.data.client.dynamictable.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.common.client.dto.command.AbstractIdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 动态数据表格 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Data
@Schema
public class DynamicTableDataDeleteCommand extends AbstractIdCommand {

    @NotNull(message = "动态数据表格id 不能为空")
    @Schema(description = "动态数据表格id")
    private Long dynamicTableId;

}
