package com.particle.lowcode.app.generator.executor;

import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentGenAppStructMapping;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;
import com.particle.lowcode.domain.generator.LowcodeSegmentGen;
import com.particle.lowcode.domain.generator.LowcodeSegmentGenId;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentGenGateway;
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
 * 低代码生成 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Component
@Validated
public class LowcodeSegmentGenDeleteCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeSegmentGenGateway lowcodeSegmentGenGateway;

	/**
	 * 执行 低代码生成 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentGenVO> execute(@Valid IdCommand deleteCommand) {
		LowcodeSegmentGenId lowcodeSegmentGenId = LowcodeSegmentGenId.of(deleteCommand.getId());
		LowcodeSegmentGen byId = lowcodeSegmentGenGateway.getById(lowcodeSegmentGenId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		// 引用判断
		boolean delete = lowcodeSegmentGenGateway.delete(lowcodeSegmentGenId,deleteCommand);
		if (delete) {
			return SingleResponse.of(LowcodeSegmentGenAppStructMapping.instance.toLowcodeSegmentGenVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeSegmentGenGateway
	 */
	@Autowired
	public void setLowcodeSegmentGenGateway(LowcodeSegmentGenGateway lowcodeSegmentGenGateway) {
		this.lowcodeSegmentGenGateway = lowcodeSegmentGenGateway;
	}
}
