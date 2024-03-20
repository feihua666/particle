package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocParamFieldAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamField;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamFieldId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocParamFieldGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放接口文档参数字段 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Component
@Validated
public class OpenplatformDocApiDocParamFieldDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocParamFieldGateway openplatformDocApiDocParamFieldGateway;

	/**
	 * 执行 开放接口文档参数字段 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDocParamFieldId openplatformDocApiDocParamFieldId = OpenplatformDocApiDocParamFieldId.of(deleteCommand.getId());
		OpenplatformDocApiDocParamField byId = openplatformDocApiDocParamFieldGateway.getById(openplatformDocApiDocParamFieldId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDocParamFieldGateway.delete(openplatformDocApiDocParamFieldId);
		if (delete) {
			return SingleResponse.of(OpenplatformDocApiDocParamFieldAppStructMapping.instance.toOpenplatformDocApiDocParamFieldVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocParamFieldGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocParamFieldGateway(OpenplatformDocApiDocParamFieldGateway openplatformDocApiDocParamFieldGateway) {
		this.openplatformDocApiDocParamFieldGateway = openplatformDocApiDocParamFieldGateway;
	}
}
