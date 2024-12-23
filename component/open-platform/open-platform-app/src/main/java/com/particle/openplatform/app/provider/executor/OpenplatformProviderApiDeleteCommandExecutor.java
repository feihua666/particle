package com.particle.openplatform.app.provider.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.provider.structmapping.OpenplatformProviderApiAppStructMapping;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderApiVO;
import com.particle.openplatform.domain.provider.OpenplatformProviderApi;
import com.particle.openplatform.domain.provider.OpenplatformProviderApiId;
import com.particle.openplatform.domain.provider.gateway.OpenplatformProviderApiGateway;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台供应商接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Component
@Validated
public class OpenplatformProviderApiDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderApiGateway openplatformProviderApiGateway;
	private IOpenplatformProviderApiService iOpenplatformProviderApiService;

	/**
	 * 执行 开放平台供应商接口 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderApiVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformProviderApiId openplatformProviderApiId = OpenplatformProviderApiId.of(deleteCommand.getId());
		OpenplatformProviderApi byId = openplatformProviderApiGateway.getById(openplatformProviderApiId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformProviderApiGateway.delete(openplatformProviderApiId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformProviderApiAppStructMapping.instance.toOpenplatformProviderApiVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformProviderApiGateway
	 */
	@Autowired
	public void setOpenplatformProviderApiGateway(OpenplatformProviderApiGateway openplatformProviderApiGateway) {
		this.openplatformProviderApiGateway = openplatformProviderApiGateway;
	}
	@Autowired
	public void setIOpenplatformProviderApiService(IOpenplatformProviderApiService iOpenplatformProviderApiService) {
		this.iOpenplatformProviderApiService = iOpenplatformProviderApiService;
	}
}
