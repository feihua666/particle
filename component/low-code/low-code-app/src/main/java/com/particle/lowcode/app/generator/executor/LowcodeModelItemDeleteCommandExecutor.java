package com.particle.lowcode.app.generator.executor;

import com.particle.lowcode.app.generator.structmapping.LowcodeModelItemAppStructMapping;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;
import com.particle.lowcode.domain.generator.LowcodeModelItem;
import com.particle.lowcode.domain.generator.LowcodeModelItemId;
import com.particle.lowcode.domain.generator.gateway.LowcodeModelItemGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 低代码模型项目 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Component
@Validated
public class LowcodeModelItemDeleteCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeModelItemGateway lowcodeModelItemGateway;

	/**
	 * 执行 低代码模型项目 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<LowcodeModelItemVO> execute(@Valid IdCommand deleteCommand) {
		LowcodeModelItemId lowcodeModelItemId = LowcodeModelItemId.of(deleteCommand.getId());
		LowcodeModelItem byId = lowcodeModelItemGateway.getById(lowcodeModelItemId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = lowcodeModelItemGateway.delete(lowcodeModelItemId);
		if (delete) {
			return SingleResponse.of(LowcodeModelItemAppStructMapping.instance.toLowcodeModelItemVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeModelItemGateway
	 */
	@Autowired
	public void setLowcodeModelItemGateway(LowcodeModelItemGateway lowcodeModelItemGateway) {
		this.lowcodeModelItemGateway = lowcodeModelItemGateway;
	}
}
