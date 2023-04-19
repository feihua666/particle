package com.particle.tenant.infrastructure.userinvite.structmapping;

import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteUserRecordDO;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecord;
import com.particle.tenant.domain.userinvite.TenantUserInviteUserRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 租户用户邀请记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Mapper
public abstract class TenantUserInviteUserRecordInfrastructureStructMapping {
	public static TenantUserInviteUserRecordInfrastructureStructMapping instance = Mappers.getMapper( TenantUserInviteUserRecordInfrastructureStructMapping.class );

	protected TenantUserInviteUserRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return TenantUserInviteUserRecordId.of(id);
	}
	protected Long map(TenantUserInviteUserRecordId tenantUserInviteUserRecordId){
		if (tenantUserInviteUserRecordId == null) {
			return null;
		}
		return tenantUserInviteUserRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantUserInviteUserRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param tenantUserInviteUserRecordDO
	 * @return
	 */
	public abstract TenantUserInviteUserRecord tenantUserInviteUserRecordDOToTenantUserInviteUserRecord(@MappingTarget TenantUserInviteUserRecord tenantUserInviteUserRecord,TenantUserInviteUserRecordDO tenantUserInviteUserRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TenantUserInviteUserRecordInfrastructureStructMapping#map(TenantUserInviteUserRecordId)}
	 * @param tenantUserInviteUserRecord
	 * @return
	 */
	public abstract TenantUserInviteUserRecordDO tenantUserInviteUserRecordToTenantUserInviteUserRecordDO(TenantUserInviteUserRecord tenantUserInviteUserRecord);

}
