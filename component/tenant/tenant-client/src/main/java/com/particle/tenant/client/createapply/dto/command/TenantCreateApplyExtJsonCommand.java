package com.particle.tenant.client.createapply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 针对申请额外数据，提取一个对象
 * </p>
 *
 * @author yangwei
 * @since 2023-05-11 16:33
 */
@Data
@Schema
public class TenantCreateApplyExtJsonCommand extends AbstractBaseCommand {

	@Valid
	@NotEmpty(message = "功能功能 不能为空")
	@Schema(description = "功能功能",requiredMode = Schema.RequiredMode.REQUIRED)
	List<TenantCreateApplyExtJsonFuncApplicationCommand> funcApplications;
}
