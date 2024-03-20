package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocExampleCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocExampleCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCodeId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocExampleCodeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放接口文档示例代码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Component
@Validated
public class OpenplatformDocApiDocExampleCodeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocExampleCodeGateway openplatformDocApiDocExampleCodeGateway;

	/**
	 * 执行 开放接口文档示例代码 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocExampleCodeVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDocExampleCodeId openplatformDocApiDocExampleCodeId = OpenplatformDocApiDocExampleCodeId.of(deleteCommand.getId());
		OpenplatformDocApiDocExampleCode byId = openplatformDocApiDocExampleCodeGateway.getById(openplatformDocApiDocExampleCodeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDocExampleCodeGateway.delete(openplatformDocApiDocExampleCodeId);
		if (delete) {
			return SingleResponse.of(OpenplatformDocApiDocExampleCodeAppStructMapping.instance.toOpenplatformDocApiDocExampleCodeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocExampleCodeGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocExampleCodeGateway(OpenplatformDocApiDocExampleCodeGateway openplatformDocApiDocExampleCodeGateway) {
		this.openplatformDocApiDocExampleCodeGateway = openplatformDocApiDocExampleCodeGateway;
	}
}
