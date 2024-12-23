package com.particle.dataquery.client.datasource.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 数据查询数据源接口 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Data
@Schema
public class DataQueryDatasourceApiQueryCommand extends AbstractBaseCommand {

    @NotNull(message = "数据源接口id 不能为空")
    @Schema(description = "数据源接口id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long dataQueryDatasourceApiId;

    /**
     * 参数对象
     */
    @Schema(description = "参数对象",requiredMode = Schema.RequiredMode.REQUIRED)
    private Object param;


    /**
     * 查询参数一般在 url后面以 & 分隔
     */
    @Schema(description = "查询参数")
    private String queryString;
}
