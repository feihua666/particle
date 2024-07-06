package com.particle.openplatform.app.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.app.structmapping.OpenplatformAppOpenapiAppStructMapping;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppOpenapiVO;
import com.particle.openplatform.domain.app.OpenplatformAppOpenapi;
import com.particle.openplatform.domain.app.OpenplatformAppOpenapiId;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppOpenapiGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台应用与开放接口配置 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Component
@Validated
public class OpenplatformAppOpenapiDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformAppOpenapiGateway openplatformAppOpenapiGateway;

	/**
	 * 执行 开放平台应用与开放接口配置 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppOpenapiVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformAppOpenapiId openplatformAppOpenapiId = OpenplatformAppOpenapiId.of(deleteCommand.getId());
		OpenplatformAppOpenapi byId = openplatformAppOpenapiGateway.getById(openplatformAppOpenapiId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformAppOpenapiGateway.delete(openplatformAppOpenapiId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformAppOpenapiAppStructMapping.instance.toOpenplatformAppOpenapiVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformAppOpenapiGateway
	 */
	@Autowired
	public void setOpenplatformAppOpenapiGateway(OpenplatformAppOpenapiGateway openplatformAppOpenapiGateway) {
		this.openplatformAppOpenapiGateway = openplatformAppOpenapiGateway;
	}
}
