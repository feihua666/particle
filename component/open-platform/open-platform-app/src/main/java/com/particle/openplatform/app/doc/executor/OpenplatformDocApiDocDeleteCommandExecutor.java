package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDoc;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放接口文档 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Component
@Validated
public class OpenplatformDocApiDocDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDocGateway openplatformDocApiDocGateway;

	/**
	 * 执行 开放接口文档 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDocId openplatformDocApiDocId = OpenplatformDocApiDocId.of(deleteCommand.getId());
		OpenplatformDocApiDoc byId = openplatformDocApiDocGateway.getById(openplatformDocApiDocId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDocGateway.delete(openplatformDocApiDocId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformDocApiDocAppStructMapping.instance.toOpenplatformDocApiDocVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDocGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDocGateway(OpenplatformDocApiDocGateway openplatformDocApiDocGateway) {
		this.openplatformDocApiDocGateway = openplatformDocApiDocGateway;
	}
}
