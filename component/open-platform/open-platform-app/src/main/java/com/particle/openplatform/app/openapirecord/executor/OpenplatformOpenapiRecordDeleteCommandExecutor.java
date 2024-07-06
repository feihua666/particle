package com.particle.openplatform.app.openapirecord.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapirecord.structmapping.OpenplatformOpenapiRecordAppStructMapping;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordVO;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecord;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordId;
import com.particle.openplatform.domain.openapirecord.gateway.OpenplatformOpenapiRecordGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台开放接口调用记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Component
@Validated
public class OpenplatformOpenapiRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordGateway openplatformOpenapiRecordGateway;

	/**
	 * 执行 开放平台开放接口调用记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiRecordId openplatformOpenapiRecordId = OpenplatformOpenapiRecordId.of(deleteCommand.getId());
		OpenplatformOpenapiRecord byId = openplatformOpenapiRecordGateway.getById(openplatformOpenapiRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiRecordGateway.delete(openplatformOpenapiRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppStructMapping.instance.toOpenplatformOpenapiRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordGateway(OpenplatformOpenapiRecordGateway openplatformOpenapiRecordGateway) {
		this.openplatformOpenapiRecordGateway = openplatformOpenapiRecordGateway;
	}
}
