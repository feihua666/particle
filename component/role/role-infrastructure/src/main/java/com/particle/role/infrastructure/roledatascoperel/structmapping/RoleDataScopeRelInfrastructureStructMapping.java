package com.particle.role.infrastructure.roledatascoperel.structmapping;

import com.particle.role.infrastructure.roledatascoperel.dos.RoleDataScopeRelDO;
import com.particle.role.domain.roledatascoperel.RoleDataScopeRel;
import com.particle.role.domain.roledatascoperel.RoleDataScopeRelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 角色数据范围关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Mapper
public abstract class RoleDataScopeRelInfrastructureStructMapping {
	public static RoleDataScopeRelInfrastructureStructMapping instance = Mappers.getMapper( RoleDataScopeRelInfrastructureStructMapping.class );

	protected RoleDataScopeRelId map(Long id){
		if (id == null) {
			return null;
		}
		return RoleDataScopeRelId.of(id);
	}
	protected Long map(RoleDataScopeRelId roleDataScopeRelId){
		if (roleDataScopeRelId == null) {
			return null;
		}
		return roleDataScopeRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleDataScopeRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param roleDataScopeRelDO
	 * @return
	 */
	public abstract RoleDataScopeRel roleDataScopeRelDOToRoleDataScopeRel(@MappingTarget RoleDataScopeRel roleDataScopeRel,RoleDataScopeRelDO roleDataScopeRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleDataScopeRelInfrastructureStructMapping#map(RoleDataScopeRelId)}
	 * @param roleDataScopeRel
	 * @return
	 */
	public abstract RoleDataScopeRelDO roleDataScopeRelToRoleDataScopeRelDO(RoleDataScopeRel roleDataScopeRel);

}
