package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplate;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口文档模板 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocTemplateGateway openplatformDocApiDocTemplateGateway;

	/**
	 * 执行 开放接口文档模板 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDocTemplateId openplatformDocApiDocTemplateId = OpenplatformDocApiDocTemplateId.of(deleteCommand.getId());
		OpenplatformDocApiDocTemplate byId = openplatformDocApiDocTemplateGateway.getById(openplatformDocApiDocTemplateId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDocTemplateGateway.delete(openplatformDocApiDocTemplateId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformDocApiDocTemplateAppStructMapping.instance.toOpenplatformDocApiDocTemplateVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocTemplateGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocTemplateGateway(OpenplatformDocApiDocTemplateGateway openplatformDocApiDocTemplateGateway) {
		this.openplatformDocApiDocTemplateGateway = openplatformDocApiDocTemplateGateway;
	}
}
