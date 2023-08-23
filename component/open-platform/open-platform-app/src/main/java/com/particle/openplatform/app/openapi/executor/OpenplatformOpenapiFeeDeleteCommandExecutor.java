package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiFeeAppStructMapping;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiFeeVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFee;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFeeId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiFeeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台开放接口费用 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Component
@Validated
public class OpenplatformOpenapiFeeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiFeeGateway openplatformOpenapiFeeGateway;

	/**
	 * 执行 开放平台开放接口费用 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiFeeVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiFeeId openplatformOpenapiFeeId = OpenplatformOpenapiFeeId.of(deleteCommand.getId());
		OpenplatformOpenapiFee byId = openplatformOpenapiFeeGateway.getById(openplatformOpenapiFeeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiFeeGateway.delete(openplatformOpenapiFeeId);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiFeeAppStructMapping.instance.toOpenplatformOpenapiFeeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiFeeGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiFeeGateway(OpenplatformOpenapiFeeGateway openplatformOpenapiFeeGateway) {
		this.openplatformOpenapiFeeGateway = openplatformOpenapiFeeGateway;
	}
}
