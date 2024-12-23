package com.particle.func.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.structmapping.FuncAppStructMapping;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.domain.Func;
import com.particle.func.domain.FuncId;
import com.particle.func.domain.gateway.FuncGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 菜单功能 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
@Validated
public class FuncDeleteCommandExecutor  extends AbstractBaseExecutor {

	private FuncGateway funcGateway;

	/**
	 * 执行 菜单功能 删除指令
	 * @param funcDeleteCommand
	 * @return
	 */
	public SingleResponse<FuncVO> execute(@Valid IdCommand funcDeleteCommand) {
		FuncId funcId = FuncId.of(funcDeleteCommand.getId());
		Func byId = funcGateway.getById(funcId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = funcGateway.delete(funcId,funcDeleteCommand);
		if (delete) {
			return SingleResponse.of(FuncAppStructMapping.instance.toFuncVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param funcGateway
	 */
	@Autowired
	public void setFuncGateway(FuncGateway funcGateway) {
		this.funcGateway = funcGateway;
	}
}
