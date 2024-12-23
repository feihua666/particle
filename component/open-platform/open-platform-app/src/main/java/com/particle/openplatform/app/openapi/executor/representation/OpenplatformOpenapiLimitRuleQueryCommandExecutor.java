package com.particle.openplatform.app.openapi.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiLimitRuleAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiLimitRulePageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiLimitRuleQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiLimitRuleVO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiLimitRuleDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiLimitRuleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放平台开放接口限制规则 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Component
@Validated
public class OpenplatformOpenapiLimitRuleQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiLimitRuleService iOpenplatformOpenapiLimitRuleService;

	/**
	 * 执行 开放平台开放接口限制规则 列表查询指令
	 * @param openplatformOpenapiLimitRuleQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiLimitRuleVO> execute(@Valid OpenplatformOpenapiLimitRuleQueryListCommand openplatformOpenapiLimitRuleQueryListCommand) {
		List<OpenplatformOpenapiLimitRuleDO> openplatformOpenapiLimitRuleDO = iOpenplatformOpenapiLimitRuleService.list(openplatformOpenapiLimitRuleQueryListCommand);
		List<OpenplatformOpenapiLimitRuleVO> openplatformOpenapiLimitRuleVOs = OpenplatformOpenapiLimitRuleAppStructMapping.instance.openplatformOpenapiLimitRuleDOsToOpenplatformOpenapiLimitRuleVOs(openplatformOpenapiLimitRuleDO);
		return MultiResponse.of(openplatformOpenapiLimitRuleVOs);
	}
	/**
	 * 执行 开放平台开放接口限制规则 分页查询指令
	 * @param openplatformOpenapiLimitRulePageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiLimitRuleVO> execute(@Valid OpenplatformOpenapiLimitRulePageQueryCommand openplatformOpenapiLimitRulePageQueryCommand) {
		Page<OpenplatformOpenapiLimitRuleDO> page = iOpenplatformOpenapiLimitRuleService.listPage(openplatformOpenapiLimitRulePageQueryCommand);
		return OpenplatformOpenapiLimitRuleAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台开放接口限制规则 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiLimitRuleVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiLimitRuleDO byId = iOpenplatformOpenapiLimitRuleService.getById(detailCommand.getId());
		OpenplatformOpenapiLimitRuleVO openplatformOpenapiLimitRuleVO = OpenplatformOpenapiLimitRuleAppStructMapping.instance.openplatformOpenapiLimitRuleDOToOpenplatformOpenapiLimitRuleVO(byId);
		return SingleResponse.of(openplatformOpenapiLimitRuleVO);
	}
	/**
	 * 执行 开放平台开放接口限制规则 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiLimitRuleVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiLimitRuleDO byId = iOpenplatformOpenapiLimitRuleService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiLimitRuleVO openplatformOpenapiLimitRuleVO = OpenplatformOpenapiLimitRuleAppStructMapping.instance.openplatformOpenapiLimitRuleDOToOpenplatformOpenapiLimitRuleVO(byId);
		return SingleResponse.of(openplatformOpenapiLimitRuleVO);
	}


	@Autowired
	public void setIOpenplatformOpenapiLimitRuleService(IOpenplatformOpenapiLimitRuleService iOpenplatformOpenapiLimitRuleService) {
		this.iOpenplatformOpenapiLimitRuleService = iOpenplatformOpenapiLimitRuleService;
	}
}
