package com.particle.data.client.dynamicdata.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 动态数据指标分类数据 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-27 16:48:26
 */
@Data
@Schema
public class DynamicDataIndicatorCategoryDataPageQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "动态数据指标分类id 不能为空")
    @Schema(description = "动态数据指标分类id")
    private Long dynamicDataIndicatorCategoryId;

    @Schema(description = "是否发布")
    private Boolean isPublic;

    @Schema(description = "批次id")
    private Long batchId;
}
