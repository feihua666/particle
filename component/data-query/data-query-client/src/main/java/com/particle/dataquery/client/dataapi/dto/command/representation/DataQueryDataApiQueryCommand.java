package com.particle.dataquery.client.dataapi.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 数据查询数据接口 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-21 21:51:10
 */
@Data
@Schema
public class DataQueryDataApiQueryCommand extends AbstractBaseCommand {

    @NotNull(message = "数据接口地址 不能为空")
    @Schema(description = "数据接口地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;

    /**
     * 参数对象
     */
    @Schema(description = "参数对象")
    private Object param;

    /**
     * 查询参数一般在 url后面以 & 分隔
     */
    @Schema(description = "查询参数")
    private String queryString;

    public static DataQueryDataApiQueryCommand create(String url, Object param, String queryString) {
        DataQueryDataApiQueryCommand command = new DataQueryDataApiQueryCommand();
        command.url = url;
        command.param = param;
        command.queryString = queryString;
        return command;
    }
}
