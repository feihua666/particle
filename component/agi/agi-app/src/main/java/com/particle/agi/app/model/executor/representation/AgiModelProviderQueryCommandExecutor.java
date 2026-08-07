package com.particle.agi.app.model.executor.representation;

import com.particle.agi.app.model.structmapping.AgiModelProviderAppStructMapping;
import com.particle.agi.client.model.dto.command.representation.AgiModelProviderQueryListCommand;
import com.particle.agi.client.model.dto.data.AgiModelProviderVO;
import com.particle.agi.infrastructure.model.dos.AgiModelProviderDO;
import com.particle.agi.infrastructure.model.service.IAgiModelProviderService;
import com.particle.agi.client.model.dto.command.representation.AgiModelProviderPageQueryCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * AI模型提供商 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Component
@Validated
public class AgiModelProviderQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAgiModelProviderService iAgiModelProviderService;

	/**
	 * 执行 AI模型提供商 列表查询指令
	 * @param agiModelProviderQueryListCommand
	 * @return
	 */
	public MultiResponse<AgiModelProviderVO> execute(@Valid AgiModelProviderQueryListCommand agiModelProviderQueryListCommand) {
		List<AgiModelProviderDO> agiModelProviderDO = iAgiModelProviderService.list(agiModelProviderQueryListCommand);
		List<AgiModelProviderVO> agiModelProviderVOs = AgiModelProviderAppStructMapping.instance.agiModelProviderDOsToAgiModelProviderVOs(agiModelProviderDO);
		return MultiResponse.of(agiModelProviderVOs);
	}
	/**
	 * 执行 AI模型提供商 分页查询指令
	 * @param agiModelProviderPageQueryCommand
	 * @return
	 */
	public PageResponse<AgiModelProviderVO> execute(@Valid AgiModelProviderPageQueryCommand agiModelProviderPageQueryCommand) {
		Page<AgiModelProviderDO> page = iAgiModelProviderService.listPage(agiModelProviderPageQueryCommand);
		return AgiModelProviderAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 AI模型提供商 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AgiModelProviderVO> executeDetail(CommonIdCommand detailCommand) {
		AgiModelProviderDO byId = iAgiModelProviderService.getById(detailCommand.getId());
		AgiModelProviderVO agiModelProviderVO = AgiModelProviderAppStructMapping.instance.agiModelProviderDOToAgiModelProviderVO(byId);
		return SingleResponse.of(agiModelProviderVO);
	}
	/**
	 * 执行 AI模型提供商 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiModelProviderVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		AgiModelProviderDO byId = iAgiModelProviderService.getById(detailForUpdateCommand.getId());
		AgiModelProviderVO agiModelProviderVO = AgiModelProviderAppStructMapping.instance.agiModelProviderDOToAgiModelProviderVO(byId);
		return SingleResponse.of(agiModelProviderVO);
	}


	@Autowired
	public void setIAgiModelProviderService(IAgiModelProviderService iAgiModelProviderService) {
		this.iAgiModelProviderService = iAgiModelProviderService;
	}
}
