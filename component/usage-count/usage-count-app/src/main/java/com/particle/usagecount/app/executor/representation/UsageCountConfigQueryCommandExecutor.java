package com.particle.usagecount.app.executor.representation;

import com.particle.usagecount.app.structmapping.UsageCountConfigAppStructMapping;
import com.particle.usagecount.client.dto.command.representation.UsageCountConfigQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountConfigVO;
import com.particle.usagecount.infrastructure.dos.UsageCountConfigDO;
import com.particle.usagecount.infrastructure.service.IUsageCountConfigService;
import com.particle.usagecount.client.dto.command.representation.UsageCountConfigPageQueryCommand;
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
 * 使用次数配置 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Component
@Validated
public class UsageCountConfigQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUsageCountConfigService iUsageCountConfigService;

	/**
	 * 执行 使用次数配置 列表查询指令
	 * @param usageCountConfigQueryListCommand
	 * @return
	 */
	public MultiResponse<UsageCountConfigVO> execute(@Valid UsageCountConfigQueryListCommand usageCountConfigQueryListCommand) {
		List<UsageCountConfigDO> usageCountConfigDO = iUsageCountConfigService.list(usageCountConfigQueryListCommand);
		List<UsageCountConfigVO> usageCountConfigVOs = UsageCountConfigAppStructMapping.instance.usageCountConfigDOsToUsageCountConfigVOs(usageCountConfigDO);
		return MultiResponse.of(usageCountConfigVOs);
	}
	/**
	 * 执行 使用次数配置 分页查询指令
	 * @param usageCountConfigPageQueryCommand
	 * @return
	 */
	public PageResponse<UsageCountConfigVO> execute(@Valid UsageCountConfigPageQueryCommand usageCountConfigPageQueryCommand) {
		Page<UsageCountConfigDO> page = iUsageCountConfigService.listPage(usageCountConfigPageQueryCommand);
		return UsageCountConfigAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 使用次数配置 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<UsageCountConfigVO> executeDetail(IdCommand detailCommand) {
		UsageCountConfigDO byId = iUsageCountConfigService.getById(detailCommand.getId());
		UsageCountConfigVO usageCountConfigVO = UsageCountConfigAppStructMapping.instance.usageCountConfigDOToUsageCountConfigVO(byId);
		return SingleResponse.of(usageCountConfigVO);
	}
	/**
	 * 执行 使用次数配置 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<UsageCountConfigVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		UsageCountConfigDO byId = iUsageCountConfigService.getById(detailForUpdateCommand.getId());
		UsageCountConfigVO usageCountConfigVO = UsageCountConfigAppStructMapping.instance.usageCountConfigDOToUsageCountConfigVO(byId);
		return SingleResponse.of(usageCountConfigVO);
	}

	@Autowired
	public void setIUsageCountConfigService(IUsageCountConfigService iUsageCountConfigService) {
		this.iUsageCountConfigService = iUsageCountConfigService;
	}
}
