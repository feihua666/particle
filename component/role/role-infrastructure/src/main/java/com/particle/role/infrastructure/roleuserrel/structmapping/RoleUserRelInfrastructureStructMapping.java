package com.particle.role.infrastructure.roleuserrel.structmapping;

import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.domain.roleuserrel.RoleUserRel;
import com.particle.role.domain.roleuserrel.RoleUserRelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 角色用户关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public abstract class RoleUserRelInfrastructureStructMapping {
	public static RoleUserRelInfrastructureStructMapping instance = Mappers.getMapper( RoleUserRelInfrastructureStructMapping.class );

	protected RoleUserRelId map(Long id){
		if (id == null) {
			return null;
		}
		return RoleUserRelId.of(id);
	}
	protected Long map(RoleUserRelId roleUserRelId){
		if (roleUserRelId == null) {
			return null;
		}
		return roleUserRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleUserRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param roleUserRelDO
	 * @return
	 */
	public abstract RoleUserRel roleUserRelDOToRoleUserRel(@MappingTarget RoleUserRel roleUserRel,RoleUserRelDO roleUserRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleUserRelInfrastructureStructMapping#map(RoleUserRelId)}
	 * @param roleUserRel
	 * @return
	 */
	public abstract RoleUserRelDO roleUserRelToRoleUserRelDO(RoleUserRel roleUserRel);

}
