package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDayRtSummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 开放平台应用开放接口日实时汇总 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway;
	private IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;

	/**
	 * 执行 开放平台应用开放接口日实时汇总 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId openplatformOpenapiRecordAppOpenapiDayRtSummaryId = OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId.of(deleteCommand.getId());
		OpenplatformOpenapiRecordAppOpenapiDayRtSummary byId = openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway.getById(openplatformOpenapiRecordAppOpenapiDayRtSummaryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway.delete(openplatformOpenapiRecordAppOpenapiDayRtSummaryId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping.instance.toOpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway = openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService(IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
	}
}
