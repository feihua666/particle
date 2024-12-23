package com.particle.openplatform.app.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.app.structmapping.OpenplatformAppAppStructMapping;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppVO;
import com.particle.openplatform.domain.app.OpenplatformApp;
import com.particle.openplatform.domain.app.OpenplatformAppId;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台应用 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Component
@Validated
public class OpenplatformAppDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformAppGateway openplatformAppGateway;

	/**
	 * 执行 开放平台应用 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformAppId openplatformAppId = OpenplatformAppId.of(deleteCommand.getId());
		OpenplatformApp byId = openplatformAppGateway.getById(openplatformAppId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformAppGateway.delete(openplatformAppId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformAppAppStructMapping.instance.toOpenplatformAppVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformAppGateway
	 */
	@Autowired
	public void setOpenplatformAppGateway(OpenplatformAppGateway openplatformAppGateway) {
		this.openplatformAppGateway = openplatformAppGateway;
	}
}
