package com.particle.data.client.dynamictable.dto.command.representation;

import com.particle.common.client.dto.command.CommonIdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 动态数据表格 导出模板指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-27 16:13:43
 */
@Data
@Schema
public class DynamicTableExportTemplateCommand extends CommonIdCommand {

    @Schema(description = "指定字段id，不指定默认全部")
    private List<Long> dynamicTableFieldIds;

    @Schema(description = "指定字段名称，不指定默认全部")
    private List<Long> dynamicTableFieldNames;

    @Schema(description = "指定字段注释，不指定默认全部")
    private List<Long> dynamicTableFieldComments;
}
