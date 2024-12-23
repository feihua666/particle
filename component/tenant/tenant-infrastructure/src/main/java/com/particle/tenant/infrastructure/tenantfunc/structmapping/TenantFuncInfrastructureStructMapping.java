package com.particle.tenant.infrastructure.tenantfunc.structmapping;

import com.particle.tenant.domain.tenantfunc.TenantFunc;
import com.particle.tenant.domain.tenantfunc.TenantFuncId;
import com.particle.tenant.infrastructure.tenantfunc.dos.TenantFuncDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 租户功能菜单 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantFuncInfrastructureStructMapping {
	public static TenantFuncInfrastructureStructMapping instance = Mappers.getMapper( TenantFuncInfrastructureStructMapping.class );

	protected TenantFuncId map(Long id){
		if (id == null) {
			return null;
		}
		return TenantFuncId.of(id);
	}
	protected Long map(TenantFuncId tenantFuncId){
		if (tenantFuncId == null) {
			return null;
		}
		return tenantFuncId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantFuncInfrastructureStructMapping#map(java.lang.Long)}
	 * @param tenantFuncDO
	 * @return
	 */
	public abstract TenantFunc tenantFuncDOToTenantFunc(@MappingTarget TenantFunc tenantFunc,TenantFuncDO tenantFuncDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantFuncInfrastructureStructMapping#map(TenantFuncId)}
	 * @param tenantFunc
	 * @return
	 */
	public abstract TenantFuncDO tenantFuncToTenantFuncDO(TenantFunc tenantFunc);

}
