package com.particle.config.app.system.executor.representation;

import com.particle.config.app.system.structmapping.SystemConfigAppStructMapping;
import com.particle.config.client.system.dto.command.representation.SystemConfigQueryListCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.config.infrastructure.system.service.ISystemConfigService;
import com.particle.config.client.system.dto.command.representation.SystemConfigPageQueryCommand;
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
 * 系统参数配置 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Component
@Validated
public class SystemConfigQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ISystemConfigService iSystemConfigService;

	/**
	 * 执行 系统参数配置 列表查询指令
	 * @param systemConfigQueryListCommand
	 * @return
	 */
	public MultiResponse<SystemConfigVO> execute(@Valid SystemConfigQueryListCommand systemConfigQueryListCommand) {
		List<SystemConfigDO> systemConfigDO = iSystemConfigService.list(systemConfigQueryListCommand);
		List<SystemConfigVO> systemConfigVOs = SystemConfigAppStructMapping.instance.systemConfigDOsToSystemConfigVOs(systemConfigDO);
		return MultiResponse.of(systemConfigVOs);
	}
	/**
	 * 执行 系统参数配置 分页查询指令
	 * @param systemConfigPageQueryCommand
	 * @return
	 */
	public PageResponse<SystemConfigVO> execute(@Valid SystemConfigPageQueryCommand systemConfigPageQueryCommand) {
		Page<SystemConfigDO> page = iSystemConfigService.listPage(systemConfigPageQueryCommand);
		return SystemConfigAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 系统参数配置 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<SystemConfigVO> executeDetail(IdCommand detailCommand) {
		SystemConfigDO byId = iSystemConfigService.getById(detailCommand.getId());
		SystemConfigVO systemConfigVO = SystemConfigAppStructMapping.instance.systemConfigDOToSystemConfigVO(byId);
		return SingleResponse.of(systemConfigVO);
	}
	/**
	 * 执行 系统参数配置 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<SystemConfigVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		SystemConfigDO byId = iSystemConfigService.getById(detailForUpdateCommand.getId());
		SystemConfigVO systemConfigVO = SystemConfigAppStructMapping.instance.systemConfigDOToSystemConfigVO(byId);
		return SingleResponse.of(systemConfigVO);
	}

	@Autowired
	public void setISystemConfigService(ISystemConfigService iSystemConfigService) {
		this.iSystemConfigService = iSystemConfigService;
	}
}
