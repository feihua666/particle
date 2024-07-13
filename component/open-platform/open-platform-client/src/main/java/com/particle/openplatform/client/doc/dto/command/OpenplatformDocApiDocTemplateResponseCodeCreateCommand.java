package com.particle.openplatform.client.doc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 开放接口文档模板响应码 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Data
@Schema
public class OpenplatformDocApiDocTemplateResponseCodeCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "编码 不能为空")
        @Schema(description = "编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

	@Schema(description = "业务状态码，码值")
	private String codeStatus;


    @Schema(description = "http响应码")
    private Integer httpCode;


    @NotNull(message = "是否计费 不能为空")
        @Schema(description = "是否计费",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isCharge;


    @NotEmpty(message = "字段说明 不能为空")
        @Schema(description = "字段说明",requiredMode = Schema.RequiredMode.REQUIRED)
    private String explanation;


    @NotNull(message = "开放接口文档模板id 不能为空")
        @Schema(description = "开放接口文档模板id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformDocApiDocTemplateId;


    @NotNull(message = "是否全局通用码 不能为空")
        @Schema(description = "是否全局通用码",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isGlobal;

	@Schema(description = "排序,默认按该字段升序排序")
	private Integer seq;


    @Schema(description = "描述")
    private String remark;









}