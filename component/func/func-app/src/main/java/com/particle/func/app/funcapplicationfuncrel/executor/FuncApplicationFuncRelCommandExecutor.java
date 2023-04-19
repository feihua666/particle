package com.particle.func.app.funcapplicationfuncrel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncApplicationAssignFuncCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncAssignFuncApplicationCommand;
import com.particle.func.domain.funcapplicationfuncrel.gateway.FuncApplicationFuncRelGateway;
import com.particle.func.infrastructure.funcapplicationfuncrel.dos.FuncApplicationFuncRelDO;
import com.particle.func.infrastructure.funcapplicationfuncrel.service.IFuncApplicationFuncRelService;
import com.particle.global.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 功能应用功能关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-17 14:16:56
 */
@Component
@Validated
public class FuncApplicationFuncRelCommandExecutor extends AbstractBaseExecutor {


	private FuncApplicationFuncRelGateway funcApplicationFuncRelGateway;

	private IFuncApplicationFuncRelService iFuncApplicationFuncRelService;


	/**
	 * 功能应用分配功能
	 * @param funcApplicationAssignFuncCommand
	 * @return
	 */
	public Response funcApplicationAssignFunc(@Valid FuncApplicationAssignFuncCommand funcApplicationAssignFuncCommand) {
		boolean result = iFuncApplicationFuncRelService.removeAndAssignRel(funcApplicationAssignFuncCommand.getFuncApplicationId(),
				funcApplicationAssignFuncCommand.getCheckedFuncIds(),funcApplicationAssignFuncCommand.getUncheckedFuncIds(),
				funcApplicationAssignFuncCommand.getIsLazyLoad(), FuncApplicationFuncRelDO::getFuncApplicationId,FuncApplicationFuncRelDO::getFuncId,
				(relDto)->new FuncApplicationFuncRelDO().setFuncApplicationId(relDto.getMainId()).setFuncId(relDto.getOtherId()));
		return Response.buildSuccess();
	}

	/**
	 * 功能分配功能应用
	 * @param funcAssignFuncApplicationCommand
	 * @return
	 */
	public Response funcAssignFuncApplication(@Valid FuncAssignFuncApplicationCommand funcAssignFuncApplicationCommand) {
		boolean result = iFuncApplicationFuncRelService.removeAndAssignRel(funcAssignFuncApplicationCommand.getFuncId(),
				funcAssignFuncApplicationCommand.getCheckedFuncApplicationIds(),funcAssignFuncApplicationCommand.getUncheckedFuncApplicationIds(),
				funcAssignFuncApplicationCommand.getIsLazyLoad(), FuncApplicationFuncRelDO::getFuncId,FuncApplicationFuncRelDO::getFuncApplicationId,
				(relDto)->new FuncApplicationFuncRelDO().setFuncId(relDto.getMainId()).setFuncApplicationId(relDto.getOtherId()));
		return Response.buildSuccess();
	}

	/**
	 * 注入使用set方法
	 * @param funcApplicationFuncRelGateway
	 */
	@Autowired
	public void setFuncApplicationFuncRelGateway(FuncApplicationFuncRelGateway funcApplicationFuncRelGateway) {
		this.funcApplicationFuncRelGateway = funcApplicationFuncRelGateway;
	}
	@Autowired
	public void setiFuncApplicationFuncRelService(IFuncApplicationFuncRelService iFuncApplicationFuncRelService) {
		this.iFuncApplicationFuncRelService = iFuncApplicationFuncRelService;
	}
}
