package com.particle.openplatform.app.providerrecord.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.providerrecord.structmapping.OpenplatformProviderRecordParamAppStructMapping;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordParamVO;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordParam;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordParamId;
import com.particle.openplatform.domain.providerrecord.gateway.OpenplatformProviderRecordParamGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Component
@Validated
public class OpenplatformProviderRecordParamDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordParamGateway openplatformProviderRecordParamGateway;

	/**
	 * 执行 开放平台开放接口供应商调用记录参数 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordParamVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformProviderRecordParamId openplatformProviderRecordParamId = OpenplatformProviderRecordParamId.of(deleteCommand.getId());
		OpenplatformProviderRecordParam byId = openplatformProviderRecordParamGateway.getById(openplatformProviderRecordParamId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformProviderRecordParamGateway.delete(openplatformProviderRecordParamId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformProviderRecordParamAppStructMapping.instance.toOpenplatformProviderRecordParamVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordParamGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordParamGateway(OpenplatformProviderRecordParamGateway openplatformProviderRecordParamGateway) {
		this.openplatformProviderRecordParamGateway = openplatformProviderRecordParamGateway;
	}
}
