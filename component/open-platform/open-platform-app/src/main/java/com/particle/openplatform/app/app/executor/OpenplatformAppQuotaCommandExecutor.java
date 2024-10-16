package com.particle.openplatform.app.app.executor;

import com.particle.openplatform.domain.app.gateway.OpenplatformAppQuotaGateway;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppQuotaService;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppQuotaDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台应用额度 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Component
@Validated
public class OpenplatformAppQuotaCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformAppQuotaGateway openplatformAppQuotaGateway;
	private IOpenplatformAppQuotaService iOpenplatformAppQuotaService;
	/**
	 * 注入使用set方法
	 * @param openplatformAppQuotaGateway
	 */
	@Autowired
	public void setOpenplatformAppQuotaGateway(OpenplatformAppQuotaGateway openplatformAppQuotaGateway) {
		this.openplatformAppQuotaGateway = openplatformAppQuotaGateway;
	}
	@Autowired
	public void setIOpenplatformAppQuotaService(IOpenplatformAppQuotaService iOpenplatformAppQuotaService) {
		this.iOpenplatformAppQuotaService = iOpenplatformAppQuotaService;
	}
}
