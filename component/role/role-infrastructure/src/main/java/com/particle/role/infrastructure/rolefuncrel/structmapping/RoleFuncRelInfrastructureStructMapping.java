package com.particle.role.infrastructure.rolefuncrel.structmapping;

import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.domain.rolefuncrel.RoleFuncRel;
import com.particle.role.domain.rolefuncrel.RoleFuncRelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 角色菜单功能关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public abstract class RoleFuncRelInfrastructureStructMapping {
	public static RoleFuncRelInfrastructureStructMapping instance = Mappers.getMapper( RoleFuncRelInfrastructureStructMapping.class );

	protected RoleFuncRelId map(Long id){
		if (id == null) {
			return null;
		}
		return RoleFuncRelId.of(id);
	}
	protected Long map(RoleFuncRelId roleFuncRelId){
		if (roleFuncRelId == null) {
			return null;
		}
		return roleFuncRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleFuncRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param roleFuncRelDO
	 * @return
	 */
	public abstract RoleFuncRel roleFuncRelDOToRoleFuncRel(@MappingTarget RoleFuncRel roleFuncRel,RoleFuncRelDO roleFuncRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link RoleFuncRelInfrastructureStructMapping#map(RoleFuncRelId)}
	 * @param roleFuncRel
	 * @return
	 */
	public abstract RoleFuncRelDO roleFuncRelToRoleFuncRelDO(RoleFuncRel roleFuncRel);

}
