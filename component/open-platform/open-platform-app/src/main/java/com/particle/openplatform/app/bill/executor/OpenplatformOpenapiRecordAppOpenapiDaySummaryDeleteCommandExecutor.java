package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDaySummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 开放平台应用开放接口日汇总 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway openplatformOpenapiRecordAppOpenapiDaySummaryGateway;
	private IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;

	/**
	 * 执行 开放平台应用开放接口日汇总 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiRecordAppOpenapiDaySummaryId openplatformOpenapiRecordAppOpenapiDaySummaryId = OpenplatformOpenapiRecordAppOpenapiDaySummaryId.of(deleteCommand.getId());
		OpenplatformOpenapiRecordAppOpenapiDaySummary byId = openplatformOpenapiRecordAppOpenapiDaySummaryGateway.getById(openplatformOpenapiRecordAppOpenapiDaySummaryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiRecordAppOpenapiDaySummaryGateway.delete(openplatformOpenapiRecordAppOpenapiDaySummaryId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping.instance.toOpenplatformOpenapiRecordAppOpenapiDaySummaryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryGateway(OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway openplatformOpenapiRecordAppOpenapiDaySummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiDaySummaryGateway = openplatformOpenapiRecordAppOpenapiDaySummaryGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiDaySummaryService(IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiDaySummaryService = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
	}
}
