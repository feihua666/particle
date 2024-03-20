package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateExampleCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateExampleCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCodeId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateExampleCodeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放接口文档模板示例代码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateExampleCodeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateExampleCodeGateway openplatformDocApiDocTemplateExampleCodeGateway;

	/**
	 * 执行 开放接口文档模板示例代码 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateExampleCodeVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDocTemplateExampleCodeId openplatformDocApiDocTemplateExampleCodeId = OpenplatformDocApiDocTemplateExampleCodeId.of(deleteCommand.getId());
		OpenplatformDocApiDocTemplateExampleCode byId = openplatformDocApiDocTemplateExampleCodeGateway.getById(openplatformDocApiDocTemplateExampleCodeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDocTemplateExampleCodeGateway.delete(openplatformDocApiDocTemplateExampleCodeId);
		if (delete) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateExampleCodeAppStructMapping.instance.toOpenplatformDocApiDocTemplateExampleCodeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateExampleCodeGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateExampleCodeGateway(OpenplatformDocApiDocTemplateExampleCodeGateway openplatformDocApiDocTemplateExampleCodeGateway) {
		this.openplatformDocApiDocTemplateExampleCodeGateway = openplatformDocApiDocTemplateExampleCodeGateway;
	}
}
