package com.particle.func.app.executor;

import com.particle.func.app.structmapping.FuncGroupAppStructMapping;
import com.particle.func.client.dto.command.FuncGroupQueryListCommand;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.func.infrastructure.dos.FuncGroupDO;
import com.particle.func.infrastructure.service.IFuncGroupService;
import com.particle.func.client.dto.command.FuncGroupPageQueryCommand;
import com.particle.func.client.dto.command.FuncGroupQueryDetailCommand;
import com.particle.func.client.dto.command.FuncGroupQueryDetailForUpdateCommand;
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
 * 功能组 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class FuncGroupQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IFuncGroupService iFuncGroupService;

	/**
	 * 执行 功能组 列表查询指令
	 * @param funcGroupQueryListCommand
	 * @return
	 */
	public MultiResponse<FuncGroupVO> execute(@Valid FuncGroupQueryListCommand funcGroupQueryListCommand) {
		List<FuncGroupDO> funcGroupDO = iFuncGroupService.list(funcGroupQueryListCommand);
		List<FuncGroupVO> funcGroupVOs = FuncGroupAppStructMapping.instance.funcGroupDOsToFuncGroupVOs(funcGroupDO);
		return MultiResponse.of(funcGroupVOs);
	}
	/**
	 * 执行 功能组 分页查询指令
	 * @param funcGroupPageQueryCommand
	 * @return
	 */
	public PageResponse<FuncGroupVO> execute(@Valid FuncGroupPageQueryCommand funcGroupPageQueryCommand) {
		Page<FuncGroupDO> page = iFuncGroupService.listPage(funcGroupPageQueryCommand);
		return FuncGroupAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 功能组 展示用详情查询指令
	 * @param funcGroupQueryDetailCommand
	 * @return
	 */
	public SingleResponse<FuncGroupVO> execute(FuncGroupQueryDetailCommand funcGroupQueryDetailCommand) {
		FuncGroupDO byId = iFuncGroupService.getById(funcGroupQueryDetailCommand.getId());
		FuncGroupVO funcGroupVO = FuncGroupAppStructMapping.instance.funcGroupDOToFuncGroupVO(byId);
		return SingleResponse.of(funcGroupVO);
	}
	/**
	 * 执行 功能组 更新用详情查询指令
	 * @param funcGroupQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<FuncGroupVO> execute(FuncGroupQueryDetailForUpdateCommand funcGroupQueryDetailForUpdateCommand) {
		FuncGroupDO byId = iFuncGroupService.getById(funcGroupQueryDetailForUpdateCommand.getId());
		FuncGroupVO funcGroupVO = FuncGroupAppStructMapping.instance.funcGroupDOToFuncGroupVO(byId);
		return SingleResponse.of(funcGroupVO);
	}

	@Autowired
	public void setIFuncGroupService(IFuncGroupService iFuncGroupService) {
		this.iFuncGroupService = iFuncGroupService;
	}
}
