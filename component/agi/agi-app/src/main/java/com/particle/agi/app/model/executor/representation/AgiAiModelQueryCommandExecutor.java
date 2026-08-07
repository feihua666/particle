package com.particle.agi.app.model.executor.representation;

import com.particle.agi.app.model.structmapping.AgiAiModelAppStructMapping;
import com.particle.agi.client.model.dto.command.representation.AgiAiModelQueryListCommand;
import com.particle.agi.client.model.dto.data.AgiAiModelVO;
import com.particle.agi.infrastructure.model.dos.AgiAiModelDO;
import com.particle.agi.infrastructure.model.service.IAgiAiModelService;
import com.particle.agi.client.model.dto.command.representation.AgiAiModelPageQueryCommand;
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
 * AI模型 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Component
@Validated
public class AgiAiModelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAgiAiModelService iAgiAiModelService;

	/**
	 * 执行 AI模型 列表查询指令
	 * @param agiAiModelQueryListCommand
	 * @return
	 */
	public MultiResponse<AgiAiModelVO> execute(@Valid AgiAiModelQueryListCommand agiAiModelQueryListCommand) {
		List<AgiAiModelDO> agiAiModelDO = iAgiAiModelService.list(agiAiModelQueryListCommand);
		List<AgiAiModelVO> agiAiModelVOs = AgiAiModelAppStructMapping.instance.agiAiModelDOsToAgiAiModelVOs(agiAiModelDO);
		return MultiResponse.of(agiAiModelVOs);
	}
	/**
	 * 执行 AI模型 分页查询指令
	 * @param agiAiModelPageQueryCommand
	 * @return
	 */
	public PageResponse<AgiAiModelVO> execute(@Valid AgiAiModelPageQueryCommand agiAiModelPageQueryCommand) {
		Page<AgiAiModelDO> page = iAgiAiModelService.listPage(agiAiModelPageQueryCommand);
		return AgiAiModelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 AI模型 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AgiAiModelVO> executeDetail(CommonIdCommand detailCommand) {
		AgiAiModelDO byId = iAgiAiModelService.getById(detailCommand.getId());
		AgiAiModelVO agiAiModelVO = AgiAiModelAppStructMapping.instance.agiAiModelDOToAgiAiModelVO(byId);
		return SingleResponse.of(agiAiModelVO);
	}
	/**
	 * 执行 AI模型 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AgiAiModelVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		AgiAiModelDO byId = iAgiAiModelService.getById(detailForUpdateCommand.getId());
		AgiAiModelVO agiAiModelVO = AgiAiModelAppStructMapping.instance.agiAiModelDOToAgiAiModelVO(byId);
		return SingleResponse.of(agiAiModelVO);
	}


	@Autowired
	public void setIAgiAiModelService(IAgiAiModelService iAgiAiModelService) {
		this.iAgiAiModelService = iAgiAiModelService;
	}
}
