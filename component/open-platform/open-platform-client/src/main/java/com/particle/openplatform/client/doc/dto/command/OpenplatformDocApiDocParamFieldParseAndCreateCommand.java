package com.particle.openplatform.client.doc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放接口文档参数字段 解析并创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-28 15:01:15
 */
@Data
@Schema
public class OpenplatformDocApiDocParamFieldParseAndCreateCommand extends AbstractBaseCommand {


    @NotNull(message = "开放接口文档id 不能为空")
    @Schema(description = "开放接口文档id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformDocApiDocId;


    @NotNull(message = "分类 不能为空")
    @Schema(description = "分类",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoryDictId;

    @NotEmpty(message = "json数据字符串 不能为空")
    @Schema(description = "json数据字符串",requiredMode = Schema.RequiredMode.REQUIRED)
    private String jsonDataStr;
}
