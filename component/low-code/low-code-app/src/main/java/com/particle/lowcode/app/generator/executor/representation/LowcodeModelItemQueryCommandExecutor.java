package com.particle.lowcode.app.generator.executor.representation;

import com.particle.lowcode.app.generator.structmapping.LowcodeModelItemAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelItemQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelItemDO;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeModelItemService;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelItemPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 低代码模型项目 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-01-05
 */
@Component
@Validated
public class LowcodeModelItemQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ILowcodeModelItemService iLowcodeModelItemService;

	/**
	 * 执行 低代码模型项目 列表查询指令
	 * @param lowcodeModelItemQueryListCommand
	 * @return
	 */
	public MultiResponse<LowcodeModelItemVO> execute(@Valid LowcodeModelItemQueryListCommand lowcodeModelItemQueryListCommand) {
		List<LowcodeModelItemDO> lowcodeModelItemDO = iLowcodeModelItemService.list(lowcodeModelItemQueryListCommand);
		List<LowcodeModelItemVO> lowcodeModelItemVOs = LowcodeModelItemAppStructMapping.instance.lowcodeModelItemDOsToLowcodeModelItemVOs(lowcodeModelItemDO);
		return MultiResponse.of(lowcodeModelItemVOs);
	}
	/**
	 * 执行 低代码模型项目 分页查询指令
	 * @param lowcodeModelItemPageQueryCommand
	 * @return
	 */
	public PageResponse<LowcodeModelItemVO> execute(@Valid LowcodeModelItemPageQueryCommand lowcodeModelItemPageQueryCommand) {
		Page<LowcodeModelItemDO> page = iLowcodeModelItemService.listPage(lowcodeModelItemPageQueryCommand);
		return LowcodeModelItemAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 低代码模型项目 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<LowcodeModelItemVO> executeDetail(IdCommand detailCommand) {
		LowcodeModelItemDO byId = iLowcodeModelItemService.getById(detailCommand.getId());
		LowcodeModelItemVO lowcodeModelItemVO = LowcodeModelItemAppStructMapping.instance.lowcodeModelItemDOToLowcodeModelItemVO(byId);
		return SingleResponse.of(lowcodeModelItemVO);
	}
	/**
	 * 执行 低代码模型项目 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<LowcodeModelItemVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		LowcodeModelItemDO byId = iLowcodeModelItemService.getById(detailForUpdateCommand.getId());
		LowcodeModelItemVO lowcodeModelItemVO = LowcodeModelItemAppStructMapping.instance.lowcodeModelItemDOToLowcodeModelItemVO(byId);
		return SingleResponse.of(lowcodeModelItemVO);
	}

	@Autowired
	public void setILowcodeModelItemService(ILowcodeModelItemService iLowcodeModelItemService) {
		this.iLowcodeModelItemService = iLowcodeModelItemService;
	}
}
