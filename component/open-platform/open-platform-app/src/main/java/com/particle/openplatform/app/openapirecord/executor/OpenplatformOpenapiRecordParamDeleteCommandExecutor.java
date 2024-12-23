package com.particle.openplatform.app.openapirecord.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapirecord.structmapping.OpenplatformOpenapiRecordParamAppStructMapping;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordParamVO;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordParam;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordParamId;
import com.particle.openplatform.domain.openapirecord.gateway.OpenplatformOpenapiRecordParamGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台开放接口调用记录参数 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordParamDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordParamGateway openplatformOpenapiRecordParamGateway;

	/**
	 * 执行 开放平台开放接口调用记录参数 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordParamVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiRecordParamId openplatformOpenapiRecordParamId = OpenplatformOpenapiRecordParamId.of(deleteCommand.getId());
		OpenplatformOpenapiRecordParam byId = openplatformOpenapiRecordParamGateway.getById(openplatformOpenapiRecordParamId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiRecordParamGateway.delete(openplatformOpenapiRecordParamId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiRecordParamAppStructMapping.instance.toOpenplatformOpenapiRecordParamVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordParamGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordParamGateway(OpenplatformOpenapiRecordParamGateway openplatformOpenapiRecordParamGateway) {
		this.openplatformOpenapiRecordParamGateway = openplatformOpenapiRecordParamGateway;
	}
}
