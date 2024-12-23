package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordDetailVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetail;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetailId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiBatchQueryRecordDetailGateway;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口批量查询记录明细 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Component
@Validated
public class OpenplatformOpenapiBatchQueryRecordDetailDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiBatchQueryRecordDetailGateway openplatformOpenapiBatchQueryRecordDetailGateway;
	private IOpenplatformOpenapiBatchQueryRecordDetailService iOpenplatformOpenapiBatchQueryRecordDetailService;

	/**
	 * 执行 开放接口批量查询记录明细 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordDetailVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiBatchQueryRecordDetailId openplatformOpenapiBatchQueryRecordDetailId = OpenplatformOpenapiBatchQueryRecordDetailId.of(deleteCommand.getId());
		OpenplatformOpenapiBatchQueryRecordDetail byId = openplatformOpenapiBatchQueryRecordDetailGateway.getById(openplatformOpenapiBatchQueryRecordDetailId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiBatchQueryRecordDetailGateway.delete(openplatformOpenapiBatchQueryRecordDetailId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiBatchQueryRecordDetailAppStructMapping.instance.toOpenplatformOpenapiBatchQueryRecordDetailVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiBatchQueryRecordDetailGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiBatchQueryRecordDetailGateway(OpenplatformOpenapiBatchQueryRecordDetailGateway openplatformOpenapiBatchQueryRecordDetailGateway) {
		this.openplatformOpenapiBatchQueryRecordDetailGateway = openplatformOpenapiBatchQueryRecordDetailGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiBatchQueryRecordDetailService(IOpenplatformOpenapiBatchQueryRecordDetailService iOpenplatformOpenapiBatchQueryRecordDetailService) {
		this.iOpenplatformOpenapiBatchQueryRecordDetailService = iOpenplatformOpenapiBatchQueryRecordDetailService;
	}
}
