package com.particle.user.infrastructure.structmapping;

import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.domain.User;
import com.particle.user.domain.UserId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 用户 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public abstract class UserInfrastructureStructMapping {
	public static UserInfrastructureStructMapping instance = Mappers.getMapper( UserInfrastructureStructMapping.class );

	protected UserId map(Long id){
		if (id == null) {
			return null;
		}
		return UserId.of(id);
	}
	protected Long map(UserId userId){
		if (userId == null) {
			return null;
		}
		return userId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserInfrastructureStructMapping#map(java.lang.Long)}
	 * @param userDO
	 * @return
	 */
	public abstract User userDOToUser(@MappingTarget User user,UserDO userDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserInfrastructureStructMapping#map(UserId)}
	 * @param user
	 * @return
	 */
	public abstract UserDO userToUserDO(User user);

}
