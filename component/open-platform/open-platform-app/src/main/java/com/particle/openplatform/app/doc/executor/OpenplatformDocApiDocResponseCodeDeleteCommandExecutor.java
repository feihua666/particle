package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocResponseCodeAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocResponseCodeVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCodeId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocResponseCodeGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口文档响应码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Component
@Validated
public class OpenplatformDocApiDocResponseCodeDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocResponseCodeGateway openplatformDocApiDocResponseCodeGateway;

	/**
	 * 执行 开放接口文档响应码 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocResponseCodeVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDocResponseCodeId openplatformDocApiDocResponseCodeId = OpenplatformDocApiDocResponseCodeId.of(deleteCommand.getId());
		OpenplatformDocApiDocResponseCode byId = openplatformDocApiDocResponseCodeGateway.getById(openplatformDocApiDocResponseCodeId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDocResponseCodeGateway.delete(openplatformDocApiDocResponseCodeId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformDocApiDocResponseCodeAppStructMapping.instance.toOpenplatformDocApiDocResponseCodeVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocResponseCodeGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocResponseCodeGateway(OpenplatformDocApiDocResponseCodeGateway openplatformDocApiDocResponseCodeGateway) {
		this.openplatformDocApiDocResponseCodeGateway = openplatformDocApiDocResponseCodeGateway;
	}
}
