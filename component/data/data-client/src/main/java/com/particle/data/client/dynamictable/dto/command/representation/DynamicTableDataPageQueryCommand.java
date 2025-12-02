package com.particle.data.client.dynamictable.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 动态数据表格数据 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-17 19:44:26
 */
@Data
@Schema
public class DynamicTableDataPageQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "动态数据表格id 不能为空")
    @Schema(description = "动态数据表格id")
    private Long dynamicTableId;

    @Schema(description = "是否发布")
    private Boolean isPublic;

    @Schema(description = "批次id")
    private Long batchId;
}
