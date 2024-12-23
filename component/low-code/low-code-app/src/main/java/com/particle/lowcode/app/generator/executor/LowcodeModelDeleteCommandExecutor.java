package com.particle.lowcode.app.generator.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.app.generator.structmapping.LowcodeModelAppStructMapping;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import com.particle.lowcode.domain.generator.LowcodeModel;
import com.particle.lowcode.domain.generator.LowcodeModelId;
import com.particle.lowcode.domain.generator.gateway.LowcodeModelGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 低代码模型 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Component
@Validated
public class LowcodeModelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeModelGateway lowcodeModelGateway;

	/**
	 * 执行 低代码模型 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<LowcodeModelVO> execute(@Valid IdCommand deleteCommand) {
		LowcodeModelId lowcodeModelId = LowcodeModelId.of(deleteCommand.getId());
		LowcodeModel byId = lowcodeModelGateway.getById(lowcodeModelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = lowcodeModelGateway.delete(lowcodeModelId,deleteCommand);
		if (delete) {
			return SingleResponse.of(LowcodeModelAppStructMapping.instance.toLowcodeModelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeModelGateway
	 */
	@Autowired
	public void setLowcodeModelGateway(LowcodeModelGateway lowcodeModelGateway) {
		this.lowcodeModelGateway = lowcodeModelGateway;
	}
}
