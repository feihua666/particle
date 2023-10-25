package com.particle.usagecount.app.executor.representation;

import com.particle.usagecount.app.structmapping.UsageCountDefineAppStructMapping;
import com.particle.usagecount.client.dto.command.representation.UsageCountDefineQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountDefineVO;
import com.particle.usagecount.infrastructure.dos.UsageCountDefineDO;
import com.particle.usagecount.infrastructure.service.IUsageCountDefineService;
import com.particle.usagecount.client.dto.command.representation.UsageCountDefinePageQueryCommand;
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
 * 使用次数定义 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Component
@Validated
public class UsageCountDefineQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IUsageCountDefineService iUsageCountDefineService;

	/**
	 * 执行 使用次数定义 列表查询指令
	 * @param usageCountDefineQueryListCommand
	 * @return
	 */
	public MultiResponse<UsageCountDefineVO> execute(@Valid UsageCountDefineQueryListCommand usageCountDefineQueryListCommand) {
		List<UsageCountDefineDO> usageCountDefineDO = iUsageCountDefineService.list(usageCountDefineQueryListCommand);
		List<UsageCountDefineVO> usageCountDefineVOs = UsageCountDefineAppStructMapping.instance.usageCountDefineDOsToUsageCountDefineVOs(usageCountDefineDO);
		return MultiResponse.of(usageCountDefineVOs);
	}
	/**
	 * 执行 使用次数定义 分页查询指令
	 * @param usageCountDefinePageQueryCommand
	 * @return
	 */
	public PageResponse<UsageCountDefineVO> execute(@Valid UsageCountDefinePageQueryCommand usageCountDefinePageQueryCommand) {
		Page<UsageCountDefineDO> page = iUsageCountDefineService.listPage(usageCountDefinePageQueryCommand);
		return UsageCountDefineAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 使用次数定义 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<UsageCountDefineVO> executeDetail(IdCommand detailCommand) {
		UsageCountDefineDO byId = iUsageCountDefineService.getById(detailCommand.getId());
		UsageCountDefineVO usageCountDefineVO = UsageCountDefineAppStructMapping.instance.usageCountDefineDOToUsageCountDefineVO(byId);
		return SingleResponse.of(usageCountDefineVO);
	}
	/**
	 * 执行 使用次数定义 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<UsageCountDefineVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		UsageCountDefineDO byId = iUsageCountDefineService.getById(detailForUpdateCommand.getId());
		UsageCountDefineVO usageCountDefineVO = UsageCountDefineAppStructMapping.instance.usageCountDefineDOToUsageCountDefineVO(byId);
		return SingleResponse.of(usageCountDefineVO);
	}

	@Autowired
	public void setIUsageCountDefineService(IUsageCountDefineService iUsageCountDefineService) {
		this.iUsageCountDefineService = iUsageCountDefineService;
	}
}
