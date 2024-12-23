package com.particle.user.infrastructure.login.structmapping;

import com.particle.user.domain.login.UserLoginRecord;
import com.particle.user.domain.login.UserLoginRecordId;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 用户登录记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserLoginRecordInfrastructureStructMapping {
	public static UserLoginRecordInfrastructureStructMapping instance = Mappers.getMapper( UserLoginRecordInfrastructureStructMapping.class );

	protected UserLoginRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return UserLoginRecordId.of(id);
	}
	protected Long map(UserLoginRecordId userLoginRecordId){
		if (userLoginRecordId == null) {
			return null;
		}
		return userLoginRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserLoginRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param userLoginRecordDO
	 * @return
	 */
	public abstract UserLoginRecord userLoginRecordDOToUserLoginRecord(@MappingTarget UserLoginRecord userLoginRecord,UserLoginRecordDO userLoginRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserLoginRecordInfrastructureStructMapping#map(UserLoginRecordId)}
	 * @param userLoginRecord
	 * @return
	 */
	public abstract UserLoginRecordDO userLoginRecordToUserLoginRecordDO(UserLoginRecord userLoginRecord);

}
