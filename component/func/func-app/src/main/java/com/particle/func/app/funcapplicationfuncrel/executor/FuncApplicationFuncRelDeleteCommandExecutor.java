package com.particle.func.app.funcapplicationfuncrel.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.funcapplicationfuncrel.structmapping.FuncApplicationFuncRelAppStructMapping;
import com.particle.func.client.funcapplicationfuncrel.dto.data.FuncApplicationFuncRelVO;
import com.particle.func.domain.funcapplicationfuncrel.FuncApplicationFuncRel;
import com.particle.func.domain.funcapplicationfuncrel.FuncApplicationFuncRelId;
import com.particle.func.domain.funcapplicationfuncrel.gateway.FuncApplicationFuncRelGateway;
import com.particle.func.infrastructure.funcapplicationfuncrel.dos.FuncApplicationFuncRelDO;
import com.particle.func.infrastructure.funcapplicationfuncrel.service.IFuncApplicationFuncRelService;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
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
 * @since 2023-04-17 10:15:29
 */
@Component
@Validated
public class FuncApplicationFuncRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private FuncApplicationFuncRelGateway funcApplicationFuncRelGateway;
	
	private IFuncApplicationFuncRelService iFuncApplicationFuncRelService;

	/**
	 * 执行 功能应用功能关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<FuncApplicationFuncRelVO> execute(@Valid IdCommand deleteCommand) {
		FuncApplicationFuncRelId funcApplicationFuncRelId = FuncApplicationFuncRelId.of(deleteCommand.getId());
		FuncApplicationFuncRel byId = funcApplicationFuncRelGateway.getById(funcApplicationFuncRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = funcApplicationFuncRelGateway.delete(funcApplicationFuncRelId,deleteCommand);
		if (delete) {
			return SingleResponse.of(FuncApplicationFuncRelAppStructMapping.instance.toFuncApplicationFuncRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 根据 funcApplicationId 删除
	 * @param funcApplicationIdCommand
	 * @return
	 */
	public Response deleteByFuncApplicationId(@Valid IdCommand funcApplicationIdCommand) {
		boolean result = iFuncApplicationFuncRelService.deleteByColumn(funcApplicationIdCommand.getId(), FuncApplicationFuncRelDO::getFuncApplicationId);
		return Response.buildSuccess();
	}
	/**
	 * 根据 funcId 删除
	 * @param funcIdCommand
	 * @return
	 */
	public Response deleteByFuncId(@Valid IdCommand funcIdCommand) {
		boolean result = iFuncApplicationFuncRelService.deleteByColumn(funcIdCommand.getId(), FuncApplicationFuncRelDO::getFuncId);
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
