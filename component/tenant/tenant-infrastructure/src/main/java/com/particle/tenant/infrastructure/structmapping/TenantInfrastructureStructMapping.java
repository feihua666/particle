package com.particle.tenant.infrastructure.structmapping;

import com.particle.tenant.domain.Tenant;
import com.particle.tenant.domain.TenantId;
import com.particle.tenant.infrastructure.dos.TenantDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 租户 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantInfrastructureStructMapping {
	public static TenantInfrastructureStructMapping instance = Mappers.getMapper( TenantInfrastructureStructMapping.class );

	protected TenantId map(Long id){
		if (id == null) {
			return null;
		}
		return TenantId.of(id);
	}
	protected Long map(TenantId tenantId){
		if (tenantId == null) {
			return null;
		}
		return tenantId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantInfrastructureStructMapping#map(java.lang.Long)}
	 * @param tenantDO
	 * @return
	 */
	public abstract Tenant tenantDOToTenant(@MappingTarget Tenant tenant,TenantDO tenantDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantInfrastructureStructMapping#map(TenantId)}
	 * @param tenant
	 * @return
	 */
	public abstract TenantDO tenantToTenantDO(Tenant tenant);

}
