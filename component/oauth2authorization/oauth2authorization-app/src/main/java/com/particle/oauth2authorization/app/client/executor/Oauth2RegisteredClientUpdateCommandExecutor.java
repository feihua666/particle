package com.particle.oauth2authorization.app.client.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.oauth2authorization.app.client.structmapping.Oauth2RegisteredClientAppStructMapping;
import com.particle.oauth2authorization.client.client.dto.command.Oauth2RegisteredClientUpdateCommand;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClient;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClientId;
import com.particle.oauth2authorization.domain.client.gateway.Oauth2RegisteredClientGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * oauth2客户端 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class Oauth2RegisteredClientUpdateCommandExecutor  extends AbstractBaseExecutor {

	private Oauth2RegisteredClientGateway oauth2RegisteredClientGateway;

	/**
	 * 执行 oauth2客户端 更新指令
	 * @param oauth2RegisteredClientUpdateCommand
	 * @return
	 */
	public SingleResponse<Oauth2RegisteredClientVO> execute(@Valid Oauth2RegisteredClientUpdateCommand oauth2RegisteredClientUpdateCommand) {
		Oauth2RegisteredClient oauth2RegisteredClient = createByOauth2RegisteredClientUpdateCommand(oauth2RegisteredClientUpdateCommand);
		oauth2RegisteredClient.setUpdateControl(oauth2RegisteredClientUpdateCommand);

		Boolean isChangeClientSecret = oauth2RegisteredClientUpdateCommand.getIsChangeClientSecret();
		if (isChangeClientSecret != null && isChangeClientSecret) {
			oauth2RegisteredClient.generateClientSecret();
		}

		boolean save = oauth2RegisteredClientGateway.save(oauth2RegisteredClient);
		if (save) {
			return SingleResponse.of(Oauth2RegisteredClientAppStructMapping.instance.toOauth2RegisteredClientVO(oauth2RegisteredClient));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据oauth2客户端更新指令创建oauth2客户端模型
	 * @param oauth2RegisteredClientUpdateCommand
	 * @return
	 */
	private Oauth2RegisteredClient createByOauth2RegisteredClientUpdateCommand(Oauth2RegisteredClientUpdateCommand oauth2RegisteredClientUpdateCommand){
		Oauth2RegisteredClient oauth2RegisteredClient = Oauth2RegisteredClient.create();
		Oauth2RegisteredClientUpdateCommandToOauth2RegisteredClientMapping.instance.fillOauth2RegisteredClientByOauth2RegisteredClientUpdateCommand(oauth2RegisteredClient, oauth2RegisteredClientUpdateCommand);
		return oauth2RegisteredClient;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface Oauth2RegisteredClientUpdateCommandToOauth2RegisteredClientMapping{
		Oauth2RegisteredClientUpdateCommandToOauth2RegisteredClientMapping instance = Mappers.getMapper(Oauth2RegisteredClientUpdateCommandToOauth2RegisteredClientMapping.class );

		default Oauth2RegisteredClientId map(Long id){
			if (id == null) {
				return null;
			}
			return Oauth2RegisteredClientId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param oauth2RegisteredClient
		 * @param oauth2RegisteredClientUpdateCommand
		 */
		void fillOauth2RegisteredClientByOauth2RegisteredClientUpdateCommand(@MappingTarget Oauth2RegisteredClient oauth2RegisteredClient, Oauth2RegisteredClientUpdateCommand oauth2RegisteredClientUpdateCommand);
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
