package com.particle.user.infrastructure.identifier.structmapping;

import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.user.domain.identifier.UserIdentifierPwd;
import com.particle.user.domain.identifier.UserIdentifierPwdId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 用户密码 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public abstract class UserIdentifierPwdInfrastructureStructMapping {
	public static UserIdentifierPwdInfrastructureStructMapping instance = Mappers.getMapper( UserIdentifierPwdInfrastructureStructMapping.class );

	protected UserIdentifierPwdId map(Long id){
		if (id == null) {
			return null;
		}
		return UserIdentifierPwdId.of(id);
	}
	protected Long map(UserIdentifierPwdId userIdentifierPwdId){
		if (userIdentifierPwdId == null) {
			return null;
		}
		return userIdentifierPwdId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserIdentifierPwdInfrastructureStructMapping#map(java.lang.Long)}
	 * @param userIdentifierPwdDO
	 * @return
	 */
	public abstract UserIdentifierPwd userIdentifierPwdDOToUserIdentifierPwd(@MappingTarget UserIdentifierPwd userIdentifierPwd,UserIdentifierPwdDO userIdentifierPwdDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserIdentifierPwdInfrastructureStructMapping#map(UserIdentifierPwdId)}
	 * @param userIdentifierPwd
	 * @return
	 */
	public abstract UserIdentifierPwdDO userIdentifierPwdToUserIdentifierPwdDO(UserIdentifierPwd userIdentifierPwd);

}
