package com.particle.usagecount.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.app.structmapping.UsageCountRecordDetailAppStructMapping;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordDetailPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordDetailQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordDetailVO;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDetailDO;
import com.particle.usagecount.infrastructure.service.IUsageCountRecordDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 使用次数记录明细 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Component
@Validated
public class UsageCountRecordDetailQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUsageCountRecordDetailService iUsageCountRecordDetailService;

	/**
	 * 执行 使用次数记录明细 列表查询指令
	 * @param usageCountRecordDetailQueryListCommand
	 * @return
	 */
	public MultiResponse<UsageCountRecordDetailVO> execute(@Valid UsageCountRecordDetailQueryListCommand usageCountRecordDetailQueryListCommand) {
		List<UsageCountRecordDetailDO> usageCountRecordDetailDO = iUsageCountRecordDetailService.list(usageCountRecordDetailQueryListCommand);
		List<UsageCountRecordDetailVO> usageCountRecordDetailVOs = UsageCountRecordDetailAppStructMapping.instance.usageCountRecordDetailDOsToUsageCountRecordDetailVOs(usageCountRecordDetailDO);
		return MultiResponse.of(usageCountRecordDetailVOs);
	}
	/**
	 * 执行 使用次数记录明细 分页查询指令
	 * @param usageCountRecordDetailPageQueryCommand
	 * @return
	 */
	public PageResponse<UsageCountRecordDetailVO> execute(@Valid UsageCountRecordDetailPageQueryCommand usageCountRecordDetailPageQueryCommand) {
		Page<UsageCountRecordDetailDO> page = iUsageCountRecordDetailService.listPage(usageCountRecordDetailPageQueryCommand);
		return UsageCountRecordDetailAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 使用次数记录明细 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<UsageCountRecordDetailVO> executeDetail(IdCommand detailCommand) {
		UsageCountRecordDetailDO byId = iUsageCountRecordDetailService.getById(detailCommand.getId());
		UsageCountRecordDetailVO usageCountRecordDetailVO = UsageCountRecordDetailAppStructMapping.instance.usageCountRecordDetailDOToUsageCountRecordDetailVO(byId);
		return SingleResponse.of(usageCountRecordDetailVO);
	}

	@Autowired
	public void setIUsageCountRecordDetailService(IUsageCountRecordDetailService iUsageCountRecordDetailService) {
		this.iUsageCountRecordDetailService = iUsageCountRecordDetailService;
	}
}
