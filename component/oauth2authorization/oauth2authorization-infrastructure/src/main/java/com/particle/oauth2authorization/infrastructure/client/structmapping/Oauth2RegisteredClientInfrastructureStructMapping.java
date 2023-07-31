package com.particle.oauth2authorization.infrastructure.client.structmapping;

import com.particle.oauth2authorization.infrastructure.client.dos.Oauth2RegisteredClientDO;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClient;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClientId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * oauth2客户端 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Mapper
public abstract class Oauth2RegisteredClientInfrastructureStructMapping {
	public static Oauth2RegisteredClientInfrastructureStructMapping instance = Mappers.getMapper( Oauth2RegisteredClientInfrastructureStructMapping.class );

	protected Oauth2RegisteredClientId map(Long id){
		if (id == null) {
			return null;
		}
		return Oauth2RegisteredClientId.of(id);
	}
	protected Long map(Oauth2RegisteredClientId oauth2RegisteredClientId){
		if (oauth2RegisteredClientId == null) {
			return null;
		}
		return oauth2RegisteredClientId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link Oauth2RegisteredClientInfrastructureStructMapping#map(java.lang.Long)}
	 * @param oauth2RegisteredClientDO
	 * @return
	 */
	public abstract Oauth2RegisteredClient oauth2RegisteredClientDOToOauth2RegisteredClient(@MappingTarget Oauth2RegisteredClient oauth2RegisteredClient,Oauth2RegisteredClientDO oauth2RegisteredClientDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link Oauth2RegisteredClientInfrastructureStructMapping#map(Oauth2RegisteredClientId)}
	 * @param oauth2RegisteredClient
	 * @return
	 */
	public abstract Oauth2RegisteredClientDO oauth2RegisteredClientToOauth2RegisteredClientDO(Oauth2RegisteredClient oauth2RegisteredClient);

}
