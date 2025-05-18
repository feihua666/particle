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
public class OpenapiCommand<P> extends Command {
    /**
     * 参数对象
     */
    @Schema(description = "参数对象")
    private P param;

    /**
     * 额外1参数对象
     */
    @Schema(description = "额外1参数对象")
    private Object ex1Param;

    /**
     * 额外2参数对象
     */
    @Schema(description = "额外2参数对象")
    private Object ex2Param;

    /**
     * 查询参数一般在 url后面以 & 分隔
     */
    @Schema(description = "查询参数")
    private String queryString;

    public static <P> OpenapiCommand<P> create(P param, String queryString) {
        OpenapiCommand<P> openapiCommand = new OpenapiCommand<>();
        openapiCommand.param = param;
        openapiCommand.queryString = queryString;
        return openapiCommand;
    }
    public static <P> OpenapiCommand<P> create(P param) {
        return create(param, null);
    }

}
