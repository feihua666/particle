package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateParamFieldAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamField;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamFieldId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateParamFieldGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口文档模板参数字段 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateParamFieldDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateParamFieldGateway openplatformDocApiDocTemplateParamFieldGateway;

	/**
	 * 执行 开放接口文档模板参数字段 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDocTemplateParamFieldId openplatformDocApiDocTemplateParamFieldId = OpenplatformDocApiDocTemplateParamFieldId.of(deleteCommand.getId());
		OpenplatformDocApiDocTemplateParamField byId = openplatformDocApiDocTemplateParamFieldGateway.getById(openplatformDocApiDocTemplateParamFieldId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDocTemplateParamFieldGateway.delete(openplatformDocApiDocTemplateParamFieldId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateParamFieldAppStructMapping.instance.toOpenplatformDocApiDocTemplateParamFieldVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateParamFieldGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateParamFieldGateway(OpenplatformDocApiDocTemplateParamFieldGateway openplatformDocApiDocTemplateParamFieldGateway) {
		this.openplatformDocApiDocTemplateParamFieldGateway = openplatformDocApiDocTemplateParamFieldGateway;
	}
}
