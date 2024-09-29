package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 开放平台应用开放接口月汇总 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway openplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
	private IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;

	/**
	 * 执行 开放平台应用开放接口月汇总 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiRecordAppOpenapiMonthSummaryId openplatformOpenapiRecordAppOpenapiMonthSummaryId = OpenplatformOpenapiRecordAppOpenapiMonthSummaryId.of(deleteCommand.getId());
		OpenplatformOpenapiRecordAppOpenapiMonthSummary byId = openplatformOpenapiRecordAppOpenapiMonthSummaryGateway.getById(openplatformOpenapiRecordAppOpenapiMonthSummaryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiRecordAppOpenapiMonthSummaryGateway.delete(openplatformOpenapiRecordAppOpenapiMonthSummaryId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping.instance.toOpenplatformOpenapiRecordAppOpenapiMonthSummaryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway(OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway openplatformOpenapiRecordAppOpenapiMonthSummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiMonthSummaryGateway = openplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiMonthSummaryService(IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
	}
}
