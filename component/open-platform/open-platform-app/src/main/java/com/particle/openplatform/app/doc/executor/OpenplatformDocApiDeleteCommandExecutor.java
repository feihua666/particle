package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApi;
import com.particle.openplatform.domain.doc.OpenplatformDocApiId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口文档接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Component
@Validated
public class OpenplatformDocApiDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiGateway openplatformDocApiGateway;

	/**
	 * 执行 开放接口文档接口 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiId openplatformDocApiId = OpenplatformDocApiId.of(deleteCommand.getId());
		OpenplatformDocApi byId = openplatformDocApiGateway.getById(openplatformDocApiId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiGateway.delete(openplatformDocApiId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformDocApiAppStructMapping.instance.toOpenplatformDocApiVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiGateway
	 */
	@Autowired
	public void setOpenplatformDocApiGateway(OpenplatformDocApiGateway openplatformDocApiGateway) {
		this.openplatformDocApiGateway = openplatformDocApiGateway;
	}
}
