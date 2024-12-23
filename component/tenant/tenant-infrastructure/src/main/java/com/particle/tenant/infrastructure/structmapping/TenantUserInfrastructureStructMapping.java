package com.particle.tenant.infrastructure.structmapping;

import com.particle.tenant.domain.TenantUser;
import com.particle.tenant.domain.TenantUserId;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 租户用户 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantUserInfrastructureStructMapping {
	public static TenantUserInfrastructureStructMapping instance = Mappers.getMapper( TenantUserInfrastructureStructMapping.class );

	protected TenantUserId map(Long id){
		if (id == null) {
			return null;
		}
		return TenantUserId.of(id);
	}
	protected Long map(TenantUserId tenantUserId){
		if (tenantUserId == null) {
			return null;
		}
		return tenantUserId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantUserInfrastructureStructMapping#map(java.lang.Long)}
	 * @param tenantUserDO
	 * @return
	 */
	public abstract TenantUser tenantUserDOToTenantUser(@MappingTarget TenantUser tenantUser,TenantUserDO tenantUserDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantUserInfrastructureStructMapping#map(TenantUserId)}
	 * @param tenantUser
	 * @return
	 */
	public abstract TenantUserDO tenantUserToTenantUserDO(TenantUser tenantUser);

}
