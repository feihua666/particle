package com.particle.usagecount.app.executor.representation;

import com.particle.usagecount.app.structmapping.UsageCountRecordAppStructMapping;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordVO;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDO;
import com.particle.usagecount.infrastructure.service.IUsageCountRecordService;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 使用次数记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Component
@Validated
public class UsageCountRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUsageCountRecordService iUsageCountRecordService;

	/**
	 * 执行 使用次数记录 列表查询指令
	 * @param usageCountRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<UsageCountRecordVO> execute(@Valid UsageCountRecordQueryListCommand usageCountRecordQueryListCommand) {
		List<UsageCountRecordDO> usageCountRecordDO = iUsageCountRecordService.list(usageCountRecordQueryListCommand);
		List<UsageCountRecordVO> usageCountRecordVOs = UsageCountRecordAppStructMapping.instance.usageCountRecordDOsToUsageCountRecordVOs(usageCountRecordDO);
		return MultiResponse.of(usageCountRecordVOs);
	}
	/**
	 * 执行 使用次数记录 分页查询指令
	 * @param usageCountRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<UsageCountRecordVO> execute(@Valid UsageCountRecordPageQueryCommand usageCountRecordPageQueryCommand) {
		Page<UsageCountRecordDO> page = iUsageCountRecordService.listPage(usageCountRecordPageQueryCommand);
		return UsageCountRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 使用次数记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<UsageCountRecordVO> executeDetail(IdCommand detailCommand) {
		UsageCountRecordDO byId = iUsageCountRecordService.getById(detailCommand.getId());
		UsageCountRecordVO usageCountRecordVO = UsageCountRecordAppStructMapping.instance.usageCountRecordDOToUsageCountRecordVO(byId);
		return SingleResponse.of(usageCountRecordVO);
	}

	@Autowired
	public void setIUsageCountRecordService(IUsageCountRecordService iUsageCountRecordService) {
		this.iUsageCountRecordService = iUsageCountRecordService;
	}
}
