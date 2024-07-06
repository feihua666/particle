package com.particle.openplatform.app.providerrecord.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.providerrecord.structmapping.OpenplatformProviderRecordAppStructMapping;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordVO;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecord;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordId;
import com.particle.openplatform.domain.providerrecord.gateway.OpenplatformProviderRecordGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台开放接口供应商调用记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Component
@Validated
public class OpenplatformProviderRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordGateway openplatformProviderRecordGateway;

	/**
	 * 执行 开放平台开放接口供应商调用记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformProviderRecordId openplatformProviderRecordId = OpenplatformProviderRecordId.of(deleteCommand.getId());
		OpenplatformProviderRecord byId = openplatformProviderRecordGateway.getById(openplatformProviderRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformProviderRecordGateway.delete(openplatformProviderRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformProviderRecordAppStructMapping.instance.toOpenplatformProviderRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordGateway(OpenplatformProviderRecordGateway openplatformProviderRecordGateway) {
		this.openplatformProviderRecordGateway = openplatformProviderRecordGateway;
	}
}
