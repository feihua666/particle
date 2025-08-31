package com.particle.user.infrastructure.structmapping;

import com.particle.user.infrastructure.dos.UserExtraInfoDO;
import com.particle.user.domain.UserExtraInfo;
import com.particle.user.domain.UserExtraInfoId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 用户扩展信息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserExtraInfoInfrastructureStructMapping {
	public static UserExtraInfoInfrastructureStructMapping instance = Mappers.getMapper( UserExtraInfoInfrastructureStructMapping.class );

	protected UserExtraInfoId map(Long id){
		if (id == null) {
			return null;
		}
		return UserExtraInfoId.of(id);
	}
	protected Long map(UserExtraInfoId userExtraInfoId){
		if (userExtraInfoId == null) {
			return null;
		}
		return userExtraInfoId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserExtraInfoInfrastructureStructMapping#map(java.lang.Long)}
	 * @param userExtraInfoDO
	 * @return
	 */
	public abstract UserExtraInfo userExtraInfoDOToUserExtraInfo(@MappingTarget UserExtraInfo userExtraInfo,UserExtraInfoDO userExtraInfoDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserExtraInfoInfrastructureStructMapping#map(UserExtraInfoId)}
	 * @param userExtraInfo
	 * @return
	 */
	public abstract UserExtraInfoDO userExtraInfoToUserExtraInfoDO(UserExtraInfo userExtraInfo);

}
