package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiAppStructMapping;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapi;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台开放接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Component
@Validated
public class OpenplatformOpenapiDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiGateway openplatformOpenapiGateway;

	/**
	 * 执行 开放平台开放接口 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiId openplatformOpenapiId = OpenplatformOpenapiId.of(deleteCommand.getId());
		OpenplatformOpenapi byId = openplatformOpenapiGateway.getById(openplatformOpenapiId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiGateway.delete(openplatformOpenapiId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiAppStructMapping.instance.toOpenplatformOpenapiVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiGateway(OpenplatformOpenapiGateway openplatformOpenapiGateway) {
		this.openplatformOpenapiGateway = openplatformOpenapiGateway;
	}
}
