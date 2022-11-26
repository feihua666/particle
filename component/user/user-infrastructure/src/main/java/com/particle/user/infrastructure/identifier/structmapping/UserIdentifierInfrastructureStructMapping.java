package com.particle.user.infrastructure.identifier.structmapping;

import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.domain.identifier.UserIdentifier;
import com.particle.user.domain.identifier.UserIdentifierId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 用户登录标识 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public abstract class UserIdentifierInfrastructureStructMapping {
	public static UserIdentifierInfrastructureStructMapping instance = Mappers.getMapper( UserIdentifierInfrastructureStructMapping.class );

	protected UserIdentifierId map(Long id){
		if (id == null) {
			return null;
		}
		return UserIdentifierId.of(id);
	}
	protected Long map(UserIdentifierId userIdentifierId){
		if (userIdentifierId == null) {
			return null;
		}
		return userIdentifierId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserIdentifierInfrastructureStructMapping#map(java.lang.Long)}
	 * @param userIdentifierDO
	 * @return
	 */
	public abstract UserIdentifier userIdentifierDOToUserIdentifier(@MappingTarget UserIdentifier userIdentifier,UserIdentifierDO userIdentifierDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserIdentifierInfrastructureStructMapping#map(UserIdentifierId)}
	 * @param userIdentifier
	 * @return
	 */
	public abstract UserIdentifierDO userIdentifierToUserIdentifierDO(UserIdentifier userIdentifier);

}
