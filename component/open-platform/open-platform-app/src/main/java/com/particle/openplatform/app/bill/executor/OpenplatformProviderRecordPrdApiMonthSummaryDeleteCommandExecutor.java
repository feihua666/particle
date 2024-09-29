package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiMonthSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummary;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdApiMonthSummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdApiMonthSummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiMonthSummaryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 开放平台供应商接口月汇总 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdApiMonthSummaryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdApiMonthSummaryGateway openplatformProviderRecordPrdApiMonthSummaryGateway;
	private IOpenplatformProviderRecordPrdApiMonthSummaryService iOpenplatformProviderRecordPrdApiMonthSummaryService;

	/**
	 * 执行 开放平台供应商接口月汇总 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformProviderRecordPrdApiMonthSummaryId openplatformProviderRecordPrdApiMonthSummaryId = OpenplatformProviderRecordPrdApiMonthSummaryId.of(deleteCommand.getId());
		OpenplatformProviderRecordPrdApiMonthSummary byId = openplatformProviderRecordPrdApiMonthSummaryGateway.getById(openplatformProviderRecordPrdApiMonthSummaryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformProviderRecordPrdApiMonthSummaryGateway.delete(openplatformProviderRecordPrdApiMonthSummaryId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping.instance.toOpenplatformProviderRecordPrdApiMonthSummaryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordPrdApiMonthSummaryGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordPrdApiMonthSummaryGateway(OpenplatformProviderRecordPrdApiMonthSummaryGateway openplatformProviderRecordPrdApiMonthSummaryGateway) {
		this.openplatformProviderRecordPrdApiMonthSummaryGateway = openplatformProviderRecordPrdApiMonthSummaryGateway;
	}
	@Autowired
	public void setIOpenplatformProviderRecordPrdApiMonthSummaryService(IOpenplatformProviderRecordPrdApiMonthSummaryService iOpenplatformProviderRecordPrdApiMonthSummaryService) {
		this.iOpenplatformProviderRecordPrdApiMonthSummaryService = iOpenplatformProviderRecordPrdApiMonthSummaryService;
	}
}
