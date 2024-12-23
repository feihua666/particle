package com.particle.openplatform.client.app.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放平台应用与开放接口配置 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Data
@Schema
public class OpenplatformAppOpenapiCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "开放平台应用id 不能为空")
        @Schema(description = "开放平台应用id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformAppId;


    @NotNull(message = "开放接口id 不能为空")
        @Schema(description = "开放接口id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformOpenapiId;


    @Schema(description = "计费id")
    private Long openplatformOpenapiFeeId;

	@Schema(description = "限制规则id，不配置不限制，应用和接口级限制")
	private Long openplatformOpenapiLimitRuleId;

	@Schema(description = "指定供应商，如果不指定将按默认编码调用")
	private Long openplatformProviderId;

	@Schema(description = "描述")
	private String remark;









}
