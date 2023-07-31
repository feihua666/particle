package com.particle.oauth2authorization.app.client.executor;

import com.particle.oauth2authorization.app.client.structmapping.Oauth2RegisteredClientAppStructMapping;
import com.particle.oauth2authorization.client.client.dto.command.Oauth2RegisteredClientCreateCommand;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClient;
import com.particle.oauth2authorization.domain.client.gateway.Oauth2RegisteredClientGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
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
public class Oauth2RegisteredClientCreateCommandExecutor  extends AbstractBaseExecutor {

	private Oauth2RegisteredClientGateway oauth2RegisteredClientGateway;

	/**
	 * 执行oauth2客户端添加指令
	 * @param oauth2RegisteredClientCreateCommand
	 * @return
	 */
	public SingleResponse<Oauth2RegisteredClientVO> execute(@Valid Oauth2RegisteredClientCreateCommand oauth2RegisteredClientCreateCommand) {
		Oauth2RegisteredClient oauth2RegisteredClient = createByOauth2RegisteredClientCreateCommand(oauth2RegisteredClientCreateCommand);
		oauth2RegisteredClient.setAddControl(oauth2RegisteredClientCreateCommand);

		oauth2RegisteredClient.generateClientId();
		oauth2RegisteredClient.generateClientSecret();
		oauth2RegisteredClient.changeClientIdIssuedAtToNow();
		oauth2RegisteredClient.changeClientSettingsToDefaultIfEmpty();
		oauth2RegisteredClient.changeTokenSettingsToDefaultIfEmpty();

		boolean save = oauth2RegisteredClientGateway.save(oauth2RegisteredClient);
		if (save) {
			return SingleResponse.of(Oauth2RegisteredClientAppStructMapping.instance.toOauth2RegisteredClientVO(oauth2RegisteredClient));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据oauth2客户端创建指令创建oauth2客户端模型
	 * @param oauth2RegisteredClientCreateCommand
	 * @return
	 */
	private Oauth2RegisteredClient createByOauth2RegisteredClientCreateCommand(Oauth2RegisteredClientCreateCommand oauth2RegisteredClientCreateCommand){
		Oauth2RegisteredClient oauth2RegisteredClient = Oauth2RegisteredClient.create();
		Oauth2RegisteredClientCreateCommandToOauth2RegisteredClientMapping.instance.fillOauth2RegisteredClientByOauth2RegisteredClientCreateCommand(oauth2RegisteredClient, oauth2RegisteredClientCreateCommand);
		return oauth2RegisteredClient;
	}

	@Mapper
	interface  Oauth2RegisteredClientCreateCommandToOauth2RegisteredClientMapping{
		Oauth2RegisteredClientCreateCommandToOauth2RegisteredClientMapping instance = Mappers.getMapper( Oauth2RegisteredClientCreateCommandToOauth2RegisteredClientMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param oauth2RegisteredClient
		 * @param oauth2RegisteredClientCreateCommand
		 */
		void fillOauth2RegisteredClientByOauth2RegisteredClientCreateCommand(@MappingTarget Oauth2RegisteredClient oauth2RegisteredClient, Oauth2RegisteredClientCreateCommand oauth2RegisteredClientCreateCommand);
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
