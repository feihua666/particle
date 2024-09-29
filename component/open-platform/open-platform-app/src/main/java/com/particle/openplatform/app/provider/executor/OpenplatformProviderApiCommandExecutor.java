package com.particle.openplatform.app.provider.executor;

import com.particle.openplatform.domain.provider.gateway.OpenplatformProviderApiGateway;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderApiService;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderApiDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台供应商接口 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Component
@Validated
public class OpenplatformProviderApiCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderApiGateway openplatformProviderApiGateway;
	private IOpenplatformProviderApiService iOpenplatformProviderApiService;
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
