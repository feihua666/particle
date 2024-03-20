package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDirRelAppStructMapping;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDirRelVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRel;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRelId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDirRelGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放接口文档接口与目录关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Component
@Validated
public class OpenplatformDocApiDirRelDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway;

	/**
	 * 执行 开放接口文档接口与目录关系 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDirRelVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformDocApiDirRelId openplatformDocApiDirRelId = OpenplatformDocApiDirRelId.of(deleteCommand.getId());
		OpenplatformDocApiDirRel byId = openplatformDocApiDirRelGateway.getById(openplatformDocApiDirRelId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformDocApiDirRelGateway.delete(openplatformDocApiDirRelId);
		if (delete) {
			return SingleResponse.of(OpenplatformDocApiDirRelAppStructMapping.instance.toOpenplatformDocApiDirRelVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiDirRelGateway
	 */
	@Autowired
	public void setOpenplatformDocApiDirRelGateway(OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway) {
		this.openplatformDocApiDirRelGateway = openplatformDocApiDirRelGateway;
	}
}
