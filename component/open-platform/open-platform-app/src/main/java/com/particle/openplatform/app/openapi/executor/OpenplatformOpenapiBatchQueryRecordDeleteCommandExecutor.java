package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiBatchQueryRecordAppStructMapping;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecord;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiBatchQueryRecordGateway;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 开放接口批量查询记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Component
@Validated
public class OpenplatformOpenapiBatchQueryRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiBatchQueryRecordGateway openplatformOpenapiBatchQueryRecordGateway;
	private IOpenplatformOpenapiBatchQueryRecordService iOpenplatformOpenapiBatchQueryRecordService;

	/**
	 * 执行 开放接口批量查询记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiBatchQueryRecordId openplatformOpenapiBatchQueryRecordId = OpenplatformOpenapiBatchQueryRecordId.of(deleteCommand.getId());
		OpenplatformOpenapiBatchQueryRecord byId = openplatformOpenapiBatchQueryRecordGateway.getById(openplatformOpenapiBatchQueryRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiBatchQueryRecordGateway.delete(openplatformOpenapiBatchQueryRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiBatchQueryRecordAppStructMapping.instance.toOpenplatformOpenapiBatchQueryRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiBatchQueryRecordGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiBatchQueryRecordGateway(OpenplatformOpenapiBatchQueryRecordGateway openplatformOpenapiBatchQueryRecordGateway) {
		this.openplatformOpenapiBatchQueryRecordGateway = openplatformOpenapiBatchQueryRecordGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiBatchQueryRecordService(IOpenplatformOpenapiBatchQueryRecordService iOpenplatformOpenapiBatchQueryRecordService) {
		this.iOpenplatformOpenapiBatchQueryRecordService = iOpenplatformOpenapiBatchQueryRecordService;
	}
}
