package com.particle.tenant.infrastructure.createapply.structmapping;

import com.particle.tenant.infrastructure.createapply.dos.TenantCreateApplyDO;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 租户创建申请 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Mapper
public abstract class TenantCreateApplyInfrastructureStructMapping {
	public static TenantCreateApplyInfrastructureStructMapping instance = Mappers.getMapper( TenantCreateApplyInfrastructureStructMapping.class );

	protected TenantCreateApplyId map(Long id){
		if (id == null) {
			return null;
		}
		return TenantCreateApplyId.of(id);
	}
	protected Long map(TenantCreateApplyId tenantCreateApplyId){
		if (tenantCreateApplyId == null) {
			return null;
		}
		return tenantCreateApplyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantCreateApplyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param tenantCreateApplyDO
	 * @return
	 */
	public abstract TenantCreateApply tenantCreateApplyDOToTenantCreateApply(@MappingTarget TenantCreateApply tenantCreateApply,TenantCreateApplyDO tenantCreateApplyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantCreateApplyInfrastructureStructMapping#map(TenantCreateApplyId)}
	 * @param tenantCreateApply
	 * @return
	 */
	public abstract TenantCreateApplyDO tenantCreateApplyToTenantCreateApplyDO(TenantCreateApply tenantCreateApply);

}
