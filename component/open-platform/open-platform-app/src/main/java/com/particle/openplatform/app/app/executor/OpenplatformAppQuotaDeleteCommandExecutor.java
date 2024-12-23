package com.particle.openplatform.app.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.app.structmapping.OpenplatformAppQuotaAppStructMapping;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppQuotaVO;
import com.particle.openplatform.domain.app.OpenplatformAppQuota;
import com.particle.openplatform.domain.app.OpenplatformAppQuotaId;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppQuotaGateway;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppQuotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台应用额度 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Component
@Validated
public class OpenplatformAppQuotaDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformAppQuotaGateway openplatformAppQuotaGateway;
	private IOpenplatformAppQuotaService iOpenplatformAppQuotaService;

	/**
	 * 执行 开放平台应用额度 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppQuotaVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformAppQuotaId openplatformAppQuotaId = OpenplatformAppQuotaId.of(deleteCommand.getId());
		OpenplatformAppQuota byId = openplatformAppQuotaGateway.getById(openplatformAppQuotaId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformAppQuotaGateway.delete(openplatformAppQuotaId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformAppQuotaAppStructMapping.instance.toOpenplatformAppQuotaVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
