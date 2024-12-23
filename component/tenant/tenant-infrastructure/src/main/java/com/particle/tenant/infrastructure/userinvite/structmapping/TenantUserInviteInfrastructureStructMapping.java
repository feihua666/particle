package com.particle.tenant.infrastructure.userinvite.structmapping;

import com.particle.tenant.domain.userinvite.TenantUserInvite;
import com.particle.tenant.domain.userinvite.TenantUserInviteId;
import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 租户用户邀请 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TenantUserInviteInfrastructureStructMapping {
	public static TenantUserInviteInfrastructureStructMapping instance = Mappers.getMapper( TenantUserInviteInfrastructureStructMapping.class );

	protected TenantUserInviteId map(Long id){
		if (id == null) {
			return null;
		}
		return TenantUserInviteId.of(id);
	}
	protected Long map(TenantUserInviteId tenantUserInviteId){
		if (tenantUserInviteId == null) {
			return null;
		}
		return tenantUserInviteId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantUserInviteInfrastructureStructMapping#map(java.lang.Long)}
	 * @param tenantUserInviteDO
	 * @return
	 */
	public abstract TenantUserInvite tenantUserInviteDOToTenantUserInvite(@MappingTarget TenantUserInvite tenantUserInvite,TenantUserInviteDO tenantUserInviteDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantUserInviteInfrastructureStructMapping#map(TenantUserInviteId)}
	 * @param tenantUserInvite
	 * @return
	 */
	public abstract TenantUserInviteDO tenantUserInviteToTenantUserInviteDO(TenantUserInvite tenantUserInvite);

}
