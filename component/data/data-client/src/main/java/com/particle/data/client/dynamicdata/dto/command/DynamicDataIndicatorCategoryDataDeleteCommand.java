package com.particle.data.client.dynamicdata.dto.command;

import com.particle.common.client.dto.command.AbstractIdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 动态数据指标分类 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-27 16:36:16
 */
@Data
@Schema
public class DynamicDataIndicatorCategoryDataDeleteCommand extends AbstractIdCommand {

    @NotNull(message = "动态数据指标分类id 不能为空")
    @Schema(description = "动态数据指标分类id")
    private Long dynamicDataIndicatorCategoryId;

}
