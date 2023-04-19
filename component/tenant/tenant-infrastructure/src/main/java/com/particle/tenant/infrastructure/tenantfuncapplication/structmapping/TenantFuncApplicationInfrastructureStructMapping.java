package com.particle.tenant.infrastructure.tenantfuncapplication.structmapping;

import com.particle.tenant.infrastructure.tenantfuncapplication.dos.TenantFuncApplicationDO;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplication;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplicationId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 租户功能应用 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Mapper
public abstract class TenantFuncApplicationInfrastructureStructMapping {
	public static TenantFuncApplicationInfrastructureStructMapping instance = Mappers.getMapper( TenantFuncApplicationInfrastructureStructMapping.class );

	protected TenantFuncApplicationId map(Long id){
		if (id == null) {
			return null;
		}
		return TenantFuncApplicationId.of(id);
	}
	protected Long map(TenantFuncApplicationId tenantFuncApplicationId){
		if (tenantFuncApplicationId == null) {
			return null;
		}
		return tenantFuncApplicationId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantFuncApplicationInfrastructureStructMapping#map(java.lang.Long)}
	 * @param tenantFuncApplicationDO
	 * @return
	 */
	public abstract TenantFuncApplication tenantFuncApplicationDOToTenantFuncApplication(@MappingTarget TenantFuncApplication tenantFuncApplication,TenantFuncApplicationDO tenantFuncApplicationDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantFuncApplicationInfrastructureStructMapping#map(TenantFuncApplicationId)}
	 * @param tenantFuncApplication
	 * @return
	 */
	public abstract TenantFuncApplicationDO tenantFuncApplicationToTenantFuncApplicationDO(TenantFuncApplication tenantFuncApplication);

}
