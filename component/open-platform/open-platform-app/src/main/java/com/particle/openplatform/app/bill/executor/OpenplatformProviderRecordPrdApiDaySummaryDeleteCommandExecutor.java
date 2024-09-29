package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiDaySummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummary;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdApiDaySummaryGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdApiDaySummaryService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiDaySummaryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 开放平台供应商接口日汇总 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdApiDaySummaryDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdApiDaySummaryGateway openplatformProviderRecordPrdApiDaySummaryGateway;
	private IOpenplatformProviderRecordPrdApiDaySummaryService iOpenplatformProviderRecordPrdApiDaySummaryService;

	/**
	 * 执行 开放平台供应商接口日汇总 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformProviderRecordPrdApiDaySummaryId openplatformProviderRecordPrdApiDaySummaryId = OpenplatformProviderRecordPrdApiDaySummaryId.of(deleteCommand.getId());
		OpenplatformProviderRecordPrdApiDaySummary byId = openplatformProviderRecordPrdApiDaySummaryGateway.getById(openplatformProviderRecordPrdApiDaySummaryId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformProviderRecordPrdApiDaySummaryGateway.delete(openplatformProviderRecordPrdApiDaySummaryId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping.instance.toOpenplatformProviderRecordPrdApiDaySummaryVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordPrdApiDaySummaryGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordPrdApiDaySummaryGateway(OpenplatformProviderRecordPrdApiDaySummaryGateway openplatformProviderRecordPrdApiDaySummaryGateway) {
		this.openplatformProviderRecordPrdApiDaySummaryGateway = openplatformProviderRecordPrdApiDaySummaryGateway;
	}
	@Autowired
	public void setIOpenplatformProviderRecordPrdApiDaySummaryService(IOpenplatformProviderRecordPrdApiDaySummaryService iOpenplatformProviderRecordPrdApiDaySummaryService) {
		this.iOpenplatformProviderRecordPrdApiDaySummaryService = iOpenplatformProviderRecordPrdApiDaySummaryService;
	}
}
