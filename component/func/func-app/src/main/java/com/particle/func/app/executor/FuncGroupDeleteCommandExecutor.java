package com.particle.func.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.app.structmapping.FuncGroupAppStructMapping;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.func.domain.FuncGroup;
import com.particle.func.domain.FuncGroupId;
import com.particle.func.domain.gateway.FuncGroupGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 功能组 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Component
@Validated
public class FuncGroupDeleteCommandExecutor  extends AbstractBaseExecutor {

	private FuncGroupGateway funcGroupGateway;

	/**
	 * 执行 功能组 删除指令
	 * @param funcGroupDeleteCommand
	 * @return
	 */
	public SingleResponse<FuncGroupVO> execute(@Valid IdCommand funcGroupDeleteCommand) {
		FuncGroupId funcGroupId = FuncGroupId.of(funcGroupDeleteCommand.getId());
		FuncGroup byId = funcGroupGateway.getById(funcGroupId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = funcGroupGateway.delete(funcGroupId,funcGroupDeleteCommand);
		if (delete) {
			return SingleResponse.of(FuncGroupAppStructMapping.instance.toFuncGroupVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param funcGroupGateway
	 */
	@Autowired
	public void setFuncGroupGateway(FuncGroupGateway funcGroupGateway) {
		this.funcGroupGateway = funcGroupGateway;
	}
}
