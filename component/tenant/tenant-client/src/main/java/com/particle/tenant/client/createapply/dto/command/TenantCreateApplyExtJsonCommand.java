package com.particle.tenant.client.createapply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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
@ApiModel
public class TenantCreateApplyExtJsonCommand extends AbstractBaseCommand {

	@Valid
	@NotEmpty(message = "功能功能 不能为空")
	@ApiModelProperty(value = "功能功能",required = true)
	List<TenantCreateApplyExtJsonFuncApplicationCommand> funcApplications;
}
