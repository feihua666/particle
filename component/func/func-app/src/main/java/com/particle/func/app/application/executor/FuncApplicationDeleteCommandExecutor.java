package com.particle.func.app.application.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.func.app.application.structmapping.FuncApplicationAppStructMapping;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
import com.particle.func.domain.application.FuncApplication;
import com.particle.func.domain.application.FuncApplicationId;
import com.particle.func.domain.application.gateway.FuncApplicationGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 功能应用 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Component
@Validated
public class FuncApplicationDeleteCommandExecutor  extends AbstractBaseExecutor {

	private FuncApplicationGateway funcApplicationGateway;

	/**
	 * 执行 功能应用 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<FuncApplicationVO> execute(@Valid IdCommand deleteCommand) {
		FuncApplicationId funcApplicationId = FuncApplicationId.of(deleteCommand.getId());
		FuncApplication byId = funcApplicationGateway.getById(funcApplicationId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = funcApplicationGateway.delete(funcApplicationId,deleteCommand);
		if (delete) {
			return SingleResponse.of(FuncApplicationAppStructMapping.instance.toFuncApplicationVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param funcApplicationGateway
	 */
	@Autowired
	public void setFuncApplicationGateway(FuncApplicationGateway funcApplicationGateway) {
		this.funcApplicationGateway = funcApplicationGateway;
	}
}
