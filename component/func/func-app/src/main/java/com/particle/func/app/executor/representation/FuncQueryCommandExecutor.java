package com.particle.func.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.structmapping.FuncAppStructMapping;
import com.particle.func.client.dto.command.representation.FuncPageQueryCommand;
import com.particle.func.client.dto.command.representation.FuncQueryListCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 菜单功能 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class FuncQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IFuncService iFuncService;

	/**
	 * 执行 菜单功能 列表查询指令
	 * @param funcQueryListCommand
	 * @return
	 */
	public MultiResponse<FuncVO> execute(@Valid FuncQueryListCommand funcQueryListCommand) {
		List<FuncDO> funcDO = iFuncService.list(funcQueryListCommand);
		List<FuncVO> funcVOs = FuncAppStructMapping.instance.funcDOsToFuncVOs(funcDO);
		return MultiResponse.of(funcVOs);
	}
	/**
	 * 执行 菜单功能 分页查询指令
	 * @param funcPageQueryCommand
	 * @return
	 */
	public PageResponse<FuncVO> execute(@Valid FuncPageQueryCommand funcPageQueryCommand) {
		Page<FuncDO> page = iFuncService.listPage(funcPageQueryCommand);
		return FuncAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 菜单功能 展示用详情查询指令
	 * @param funcQueryDetailCommand
	 * @return
	 */
	public SingleResponse<FuncVO> executeDetail(IdCommand funcQueryDetailCommand) {
		FuncDO byId = iFuncService.getById(funcQueryDetailCommand.getId());
		FuncVO funcVO = FuncAppStructMapping.instance.funcDOToFuncVO(byId);
		return SingleResponse.of(funcVO);
	}
	/**
	 * 执行 菜单功能 更新用详情查询指令
	 * @param funcQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<FuncVO> executeDetailForUpdate(IdCommand funcQueryDetailForUpdateCommand) {
		FuncDO byId = iFuncService.getById(funcQueryDetailForUpdateCommand.getId());
		FuncVO funcVO = FuncAppStructMapping.instance.funcDOToFuncVO(byId);
		return SingleResponse.of(funcVO);
	}

	/**
	 * 根据id获取
	 * @param ids
	 * @return
	 */
	public MultiResponse<FuncVO> queryListByIds(List<Long> ids){
		List<FuncDO> funcDO = iFuncService.listByIds(ids);
		List<FuncVO> funcVOs = FuncAppStructMapping.instance.funcDOsToFuncVOs(funcDO);
		return MultiResponse.of(funcVOs);
	}
	@Autowired
	public void setIFuncService(IFuncService iFuncService) {
		this.iFuncService = iFuncService;
	}
}
