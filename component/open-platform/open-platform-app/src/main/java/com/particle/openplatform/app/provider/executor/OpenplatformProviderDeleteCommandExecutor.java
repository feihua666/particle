package com.particle.openplatform.app.provider.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.provider.structmapping.OpenplatformProviderAppStructMapping;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderVO;
import com.particle.openplatform.domain.provider.OpenplatformProvider;
import com.particle.openplatform.domain.provider.OpenplatformProviderId;
import com.particle.openplatform.domain.provider.gateway.OpenplatformProviderGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台开放接口供应商 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Component
@Validated
public class OpenplatformProviderDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderGateway openplatformProviderGateway;

	/**
	 * 执行 开放平台开放接口供应商 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformProviderId openplatformProviderId = OpenplatformProviderId.of(deleteCommand.getId());
		OpenplatformProvider byId = openplatformProviderGateway.getById(openplatformProviderId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformProviderGateway.delete(openplatformProviderId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformProviderAppStructMapping.instance.toOpenplatformProviderVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformProviderGateway
	 */
	@Autowired
	public void setOpenplatformProviderGateway(OpenplatformProviderGateway openplatformProviderGateway) {
		this.openplatformProviderGateway = openplatformProviderGateway;
	}
}
