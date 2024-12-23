package com.particle.role.infrastructure.structmapping;

import com.particle.role.domain.Role;
import com.particle.role.domain.RoleId;
import com.particle.role.infrastructure.dos.RoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 角色 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RoleInfrastructureStructMapping {
	public static RoleInfrastructureStructMapping instance = Mappers.getMapper( RoleInfrastructureStructMapping.class );

	protected RoleId map(Long id){
		if (id == null) {
			return null;
		}
		return RoleId.of(id);
	}
	protected Long map(RoleId roleId){
		if (roleId == null) {
			return null;
		}
		return roleId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleInfrastructureStructMapping#map(java.lang.Long)}
	 * @param roleDO
	 * @return
	 */
	public abstract Role roleDOToRole(@MappingTarget Role role,RoleDO roleDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleInfrastructureStructMapping#map(RoleId)}
	 * @param role
	 * @return
	 */
	public abstract RoleDO roleToRoleDO(Role role);

}
