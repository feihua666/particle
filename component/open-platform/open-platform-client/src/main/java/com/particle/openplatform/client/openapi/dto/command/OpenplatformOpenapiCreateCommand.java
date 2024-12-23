package com.particle.openplatform.client.openapi.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放平台开放接口 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@PropValid
@Data
@Schema
public class OpenplatformOpenapiCreateCommand extends AbstractBaseCommand {

	@Schema(description = "编码")
	private String code;

	@NotEmpty(message = "名称 不能为空")
	@Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
	private String name;

	@NotNull(message = "是否为组 不能为空")
	@Schema(description = "是否为组", requiredMode = Schema.RequiredMode.REQUIRED)
	private Boolean isGroup;

	@PropValid.DependCondition(message = "接口权限码 不能为空",dependProp = "isGroup",ifEqual = "false")
	@Schema(description = "接口权限码")
	private String permissions;

	@Schema(description = "接口地址")
	private String url;

	@NotNull(message = "是否禁用 不能为空")
	@Schema(description = "是否禁用", requiredMode = Schema.RequiredMode.REQUIRED)
	private Boolean isDisabled;

	@Schema(description = "禁用原因")
	private String disabledReason;

	@Schema(description = "默认计费id")
	private Long defaultOpenplatformOpenapiFeeId;

	@Schema(description = "可用供应商")
	private String openplatformProviderIds;

	@Schema(description = "描述")
	private String remark;

	@Schema(description = "父级")
	private Long parentId;

}
