package com.particle.openplatform.client.doc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放接口文档示例代码 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Data
@Schema
public class OpenplatformDocApiDocExampleCodeCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "开发语言 不能为空")
        @Schema(description = "开发语言",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long langDictId;


    @NotEmpty(message = "示例代码片段 不能为空")
        @Schema(description = "示例代码片段",requiredMode = Schema.RequiredMode.REQUIRED)
    private String exampleCode;


    @Schema(description = "示例代码下载地址")
    private String exampleDownloadUrl;


    /**
     * 已在添加时处理
     */
    @Schema(description = "开放接口文档接口id")
    private Long openplatformDocApiId;

    @NotNull(message = "开放接口文档id 不能为空")
        @Schema(description = "开放接口文档id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformDocApiDocId;

	@Schema(description = "排序,默认按该字段升序排序")
	private Integer seq;


    @Schema(description = "描述")
    private String remark;









}
