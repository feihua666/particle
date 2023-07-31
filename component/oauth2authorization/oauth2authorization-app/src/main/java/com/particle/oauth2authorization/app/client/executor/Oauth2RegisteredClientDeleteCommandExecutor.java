package com.particle.oauth2authorization.app.client.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.oauth2authorization.app.client.structmapping.Oauth2RegisteredClientAppStructMapping;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClient;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClientId;
import com.particle.oauth2authorization.domain.client.gateway.Oauth2RegisteredClientGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * oauth2客户端 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Component
@Validated
public class Oauth2RegisteredClientDeleteCommandExecutor  extends AbstractBaseExecutor {

	private Oauth2RegisteredClientGateway oauth2RegisteredClientGateway;

	/**
	 * 执行 oauth2客户端 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<Oauth2RegisteredClientVO> execute(@Valid IdCommand deleteCommand) {
		Oauth2RegisteredClientId oauth2RegisteredClientId = Oauth2RegisteredClientId.of(deleteCommand.getId());
		Oauth2RegisteredClient byId = oauth2RegisteredClientGateway.getById(oauth2RegisteredClientId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = oauth2RegisteredClientGateway.delete(oauth2RegisteredClientId);
		if (delete) {
			return SingleResponse.of(Oauth2RegisteredClientAppStructMapping.instance.toOauth2RegisteredClientVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param oauth2RegisteredClientGateway
	 */
	@Autowired
	public void setOauth2RegisteredClientGateway(Oauth2RegisteredClientGateway oauth2RegisteredClientGateway) {
		this.oauth2RegisteredClientGateway = oauth2RegisteredClientGateway;
	}
}
