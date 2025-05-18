package com.particle.global.openapi.endpoint.command;

import com.particle.global.dto.basic.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 开放接口数据接口 入库 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-25 13:58:54
 */
@Data
@Schema
public class OpenapiWarehouseCommand<P> extends Command {
    /**
     * 参数对象
     */
    @Schema(description = "参数对象",requiredMode = Schema.RequiredMode.REQUIRED)
    private P param;

    public static <P> OpenapiWarehouseCommand<P> create(P param) {
        OpenapiWarehouseCommand<P> openapiCommand = new OpenapiWarehouseCommand<>();
        openapiCommand.param = param;
        return openapiCommand;
    }
}
