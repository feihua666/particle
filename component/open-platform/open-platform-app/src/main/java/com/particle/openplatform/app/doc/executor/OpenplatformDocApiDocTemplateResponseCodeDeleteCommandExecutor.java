package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateResponseCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateResponseCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCodeId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateResponseCodeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放接口文档模板响应码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateResponseCodeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateResponseCodeGateway openplatformDocApiDocTemplateResponseCodeGateway;

	/**
	 * 执行 开放接口文档模板响应码 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateResponseCodeVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDocTemplateResponseCodeId openplatformDocApiDocTemplateResponseCodeId = OpenplatformDocApiDocTemplateResponseCodeId.of(deleteCommand.getId());
		OpenplatformDocApiDocTemplateResponseCode byId = openplatformDocApiDocTemplateResponseCodeGateway.getById(openplatformDocApiDocTemplateResponseCodeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDocTemplateResponseCodeGateway.delete(openplatformDocApiDocTemplateResponseCodeId);
		if (delete) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateResponseCodeAppStructMapping.instance.toOpenplatformDocApiDocTemplateResponseCodeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateResponseCodeGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateResponseCodeGateway(OpenplatformDocApiDocTemplateResponseCodeGateway openplatformDocApiDocTemplateResponseCodeGateway) {
		this.openplatformDocApiDocTemplateResponseCodeGateway = openplatformDocApiDocTemplateResponseCodeGateway;
	}
}
