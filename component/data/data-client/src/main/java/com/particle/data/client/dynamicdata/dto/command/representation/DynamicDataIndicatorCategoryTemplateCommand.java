package com.particle.data.client.dynamicdata.dto.command.representation;

import com.particle.common.client.dto.command.IdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 动态数据指标分类 导出模板指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-27 16:13:43
 */
@Data
@Schema
public class DynamicDataIndicatorCategoryTemplateCommand extends IdCommand {

    @Schema(description = "指定指标id，不指定默认全部")
    private List<Long> dynamicDataIndicatorIds;

    @Schema(description = "指定指标名称，不指定默认全部")
    private List<Long> dynamicTableFieldNames;
}
