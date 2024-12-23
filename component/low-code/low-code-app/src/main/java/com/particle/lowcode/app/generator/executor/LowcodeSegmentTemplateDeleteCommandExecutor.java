package com.particle.lowcode.app.generator.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentTemplateAppStructMapping;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;
import com.particle.lowcode.domain.generator.LowcodeSegmentTemplate;
import com.particle.lowcode.domain.generator.LowcodeSegmentTemplateId;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentTemplateGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 低代码片段模板 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Component
@Validated
public class LowcodeSegmentTemplateDeleteCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeSegmentTemplateGateway lowcodeSegmentTemplateGateway;

	/**
	 * 执行 低代码片段模板 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentTemplateVO> execute(@Valid IdCommand deleteCommand) {
		LowcodeSegmentTemplateId lowcodeSegmentTemplateId = LowcodeSegmentTemplateId.of(deleteCommand.getId());
		LowcodeSegmentTemplate byId = lowcodeSegmentTemplateGateway.getById(lowcodeSegmentTemplateId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = lowcodeSegmentTemplateGateway.delete(lowcodeSegmentTemplateId,deleteCommand);
		if (delete) {
			return SingleResponse.of(LowcodeSegmentTemplateAppStructMapping.instance.toLowcodeSegmentTemplateVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeSegmentTemplateGateway
	 */
	@Autowired
	public void setLowcodeSegmentTemplateGateway(LowcodeSegmentTemplateGateway lowcodeSegmentTemplateGateway) {
		this.lowcodeSegmentTemplateGateway = lowcodeSegmentTemplateGateway;
	}
}
