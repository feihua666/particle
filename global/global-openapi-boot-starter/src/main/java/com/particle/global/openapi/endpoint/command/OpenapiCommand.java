package com.particle.global.openapi.endpoint.command;

import com.particle.global.dto.basic.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 开放接口数据接口 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-21 21:51:10
 */
@Data
@Schema
public class OpenapiCommand extends Command {
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
