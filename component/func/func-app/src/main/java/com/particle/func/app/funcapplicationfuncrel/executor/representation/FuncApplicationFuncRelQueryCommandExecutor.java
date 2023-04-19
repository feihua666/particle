package com.particle.func.app.funcapplicationfuncrel.executor.representation;

import com.particle.func.app.funcapplicationfuncrel.structmapping.FuncApplicationFuncRelAppStructMapping;
import com.particle.func.client.funcapplicationfuncrel.dto.command.representation.FuncApplicationFuncRelQueryListCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.data.FuncApplicationFuncRelVO;
import com.particle.func.infrastructure.funcapplicationfuncrel.dos.FuncApplicationFuncRelDO;
import com.particle.func.infrastructure.funcapplicationfuncrel.service.IFuncApplicationFuncRelService;
import com.particle.func.client.funcapplicationfuncrel.dto.command.representation.FuncApplicationFuncRelPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 功能应用功能关系 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Component
@Validated
public class FuncApplicationFuncRelQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IFuncApplicationFuncRelService iFuncApplicationFuncRelService;

	/**
	 * 执行 功能应用功能关系 列表查询指令
	 * @param funcApplicationFuncRelQueryListCommand
	 * @return
	 */
	public MultiResponse<FuncApplicationFuncRelVO> execute(@Valid FuncApplicationFuncRelQueryListCommand funcApplicationFuncRelQueryListCommand) {
		List<FuncApplicationFuncRelDO> funcApplicationFuncRelDO = iFuncApplicationFuncRelService.list(funcApplicationFuncRelQueryListCommand);
		List<FuncApplicationFuncRelVO> funcApplicationFuncRelVOs = FuncApplicationFuncRelAppStructMapping.instance.funcApplicationFuncRelDOsToFuncApplicationFuncRelVOs(funcApplicationFuncRelDO);
		return MultiResponse.of(funcApplicationFuncRelVOs);
	}
	/**
	 * 执行 功能应用功能关系 分页查询指令
	 * @param funcApplicationFuncRelPageQueryCommand
	 * @return
	 */
	public PageResponse<FuncApplicationFuncRelVO> execute(@Valid FuncApplicationFuncRelPageQueryCommand funcApplicationFuncRelPageQueryCommand) {
		Page<FuncApplicationFuncRelDO> page = iFuncApplicationFuncRelService.listPage(funcApplicationFuncRelPageQueryCommand);
		return FuncApplicationFuncRelAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 功能应用功能关系 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<FuncApplicationFuncRelVO> executeDetail(IdCommand detailCommand) {
		FuncApplicationFuncRelDO byId = iFuncApplicationFuncRelService.getById(detailCommand.getId());
		FuncApplicationFuncRelVO funcApplicationFuncRelVO = FuncApplicationFuncRelAppStructMapping.instance.funcApplicationFuncRelDOToFuncApplicationFuncRelVO(byId);
		return SingleResponse.of(funcApplicationFuncRelVO);
	}
	/**
	 * 执行 功能应用功能关系 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<FuncApplicationFuncRelVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		FuncApplicationFuncRelDO byId = iFuncApplicationFuncRelService.getById(detailForUpdateCommand.getId());
		FuncApplicationFuncRelVO funcApplicationFuncRelVO = FuncApplicationFuncRelAppStructMapping.instance.funcApplicationFuncRelDOToFuncApplicationFuncRelVO(byId);
		return SingleResponse.of(funcApplicationFuncRelVO);
	}



	/**
	 * 查询功能已分配的功能应用菜单ids
	 * @param funcApplicationIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryFuncIdsByFuncApplicationId(@Valid IdCommand funcApplicationIdCommand) {

		FuncApplicationFuncRelQueryListCommand funcApplicationFuncRelQueryListCommand = new FuncApplicationFuncRelQueryListCommand();
		funcApplicationFuncRelQueryListCommand.setFuncApplicationId(funcApplicationIdCommand.getId());
		MultiResponse<FuncApplicationFuncRelVO> funcApplicationFuncRelVOMultiResponse = execute(funcApplicationFuncRelQueryListCommand);
		if(funcApplicationFuncRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = funcApplicationFuncRelVOMultiResponse.getData().stream().map(FuncApplicationFuncRelVO::getFuncId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}
	/**
	 * 查询功能应用已分配的功能ids
	 * @param funcIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryFuncApplicationIdsByFuncId(@Valid IdCommand funcIdCommand) {

		FuncApplicationFuncRelQueryListCommand funcApplicationFuncRelQueryListCommand = new FuncApplicationFuncRelQueryListCommand();
		funcApplicationFuncRelQueryListCommand.setFuncApplicationId(funcIdCommand.getId());
		MultiResponse<FuncApplicationFuncRelVO> funcApplicationFuncRelVOMultiResponse = execute(funcApplicationFuncRelQueryListCommand);
		if(funcApplicationFuncRelVOMultiResponse.isNotEmpty()){
			List<Long> collect = funcApplicationFuncRelVOMultiResponse.getData().stream().map(FuncApplicationFuncRelVO::getFuncApplicationId).collect(Collectors.toList());
			return MultiResponse.of(collect);
		}
		return MultiResponse.buildSuccess();
	}

	@Autowired
	public void setIFuncApplicationFuncRelService(IFuncApplicationFuncRelService iFuncApplicationFuncRelService) {
		this.iFuncApplicationFuncRelService = iFuncApplicationFuncRelService;
	}
}
