package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocDirNameAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirNameVO;
import com.particle.openplatform.domain.doc.OpenplatformDocDirName;
import com.particle.openplatform.domain.doc.OpenplatformDocDirNameId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocDirNameGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放接口目录名称 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Component
@Validated
public class OpenplatformDocDirNameDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocDirNameGateway openplatformDocDirNameGateway;

	/**
	 * 执行 开放接口目录名称 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocDirNameVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocDirNameId openplatformDocDirNameId = OpenplatformDocDirNameId.of(deleteCommand.getId());
		OpenplatformDocDirName byId = openplatformDocDirNameGateway.getById(openplatformDocDirNameId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocDirNameGateway.delete(openplatformDocDirNameId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformDocDirNameAppStructMapping.instance.toOpenplatformDocDirNameVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocDirNameGateway
	 */
	@Autowired
	public void setOpenplatformDocDirNameGateway(OpenplatformDocDirNameGateway openplatformDocDirNameGateway) {
		this.openplatformDocDirNameGateway = openplatformDocDirNameGateway;
	}
}
