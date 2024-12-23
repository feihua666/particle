package com.particle.user.infrastructure.login.structmapping;

import com.particle.user.domain.login.UserLoginDevice;
import com.particle.user.domain.login.UserLoginDeviceId;
import com.particle.user.infrastructure.login.dos.UserLoginDeviceDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 用户登录设备 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserLoginDeviceInfrastructureStructMapping {
	public static UserLoginDeviceInfrastructureStructMapping instance = Mappers.getMapper( UserLoginDeviceInfrastructureStructMapping.class );

	protected UserLoginDeviceId map(Long id){
		if (id == null) {
			return null;
		}
		return UserLoginDeviceId.of(id);
	}
	protected Long map(UserLoginDeviceId userLoginDeviceId){
		if (userLoginDeviceId == null) {
			return null;
		}
		return userLoginDeviceId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserLoginDeviceInfrastructureStructMapping#map(java.lang.Long)}
	 * @param userLoginDeviceDO
	 * @return
	 */
	public abstract UserLoginDevice userLoginDeviceDOToUserLoginDevice(@MappingTarget UserLoginDevice userLoginDevice,UserLoginDeviceDO userLoginDeviceDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserLoginDeviceInfrastructureStructMapping#map(UserLoginDeviceId)}
	 * @param userLoginDevice
	 * @return
	 */
	public abstract UserLoginDeviceDO userLoginDeviceToUserLoginDeviceDO(UserLoginDevice userLoginDevice);

}
