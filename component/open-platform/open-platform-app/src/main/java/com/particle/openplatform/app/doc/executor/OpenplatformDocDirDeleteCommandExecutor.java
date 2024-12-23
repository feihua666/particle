package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocDirAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;
import com.particle.openplatform.domain.doc.OpenplatformDocDir;
import com.particle.openplatform.domain.doc.OpenplatformDocDirId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocDirGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口目录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Component
@Validated
public class OpenplatformDocDirDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocDirGateway openplatformDocDirGateway;

	/**
	 * 执行 开放接口目录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocDirVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocDirId openplatformDocDirId = OpenplatformDocDirId.of(deleteCommand.getId());
		OpenplatformDocDir byId = openplatformDocDirGateway.getById(openplatformDocDirId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocDirGateway.delete(openplatformDocDirId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformDocDirAppStructMapping.instance.toOpenplatformDocDirVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocDirGateway
	 */
	@Autowired
	public void setOpenplatformDocDirGateway(OpenplatformDocDirGateway openplatformDocDirGateway) {
		this.openplatformDocDirGateway = openplatformDocDirGateway;
	}
}
