package com.particle.lowcode.app.generator.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.app.generator.structmapping.LowcodeModelAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelDO;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeModelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 低代码模型 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-01-05
 */
@Component
@Validated
public class LowcodeModelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ILowcodeModelService iLowcodeModelService;

	/**
	 * 执行 低代码模型 列表查询指令
	 * @param lowcodeModelQueryListCommand
	 * @return
	 */
	public MultiResponse<LowcodeModelVO> execute(@Valid LowcodeModelQueryListCommand lowcodeModelQueryListCommand) {
		List<LowcodeModelDO> lowcodeModelDO = iLowcodeModelService.list(lowcodeModelQueryListCommand);
		List<LowcodeModelVO> lowcodeModelVOs = LowcodeModelAppStructMapping.instance.lowcodeModelDOsToLowcodeModelVOs(lowcodeModelDO);
		return MultiResponse.of(lowcodeModelVOs);
	}
	/**
	 * 执行 低代码模型 分页查询指令
	 * @param lowcodeModelPageQueryCommand
	 * @return
	 */
	public PageResponse<LowcodeModelVO> execute(@Valid LowcodeModelPageQueryCommand lowcodeModelPageQueryCommand) {
		Page<LowcodeModelDO> page = iLowcodeModelService.listPage(lowcodeModelPageQueryCommand);
		return LowcodeModelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 低代码模型 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<LowcodeModelVO> executeDetail(IdCommand detailCommand) {
		LowcodeModelDO byId = iLowcodeModelService.getById(detailCommand.getId());
		LowcodeModelVO lowcodeModelVO = LowcodeModelAppStructMapping.instance.lowcodeModelDOToLowcodeModelVO(byId);
		return SingleResponse.of(lowcodeModelVO);
	}
	/**
	 * 执行 低代码模型 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<LowcodeModelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		LowcodeModelDO byId = iLowcodeModelService.getById(detailForUpdateCommand.getId());
		LowcodeModelVO lowcodeModelVO = LowcodeModelAppStructMapping.instance.lowcodeModelDOToLowcodeModelVO(byId);
		return SingleResponse.of(lowcodeModelVO);
	}

	@Autowired
	public void setILowcodeModelService(ILowcodeModelService iLowcodeModelService) {
		this.iLowcodeModelService = iLowcodeModelService;
	}
}
