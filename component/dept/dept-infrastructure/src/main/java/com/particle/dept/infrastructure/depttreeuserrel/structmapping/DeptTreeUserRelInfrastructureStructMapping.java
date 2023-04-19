package com.particle.dept.infrastructure.depttreeuserrel.structmapping;

import com.particle.dept.infrastructure.depttreeuserrel.dos.DeptTreeUserRelDO;
import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRel;
import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 部门树用户关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:43
 */
@Mapper
public abstract class DeptTreeUserRelInfrastructureStructMapping {
	public static DeptTreeUserRelInfrastructureStructMapping instance = Mappers.getMapper( DeptTreeUserRelInfrastructureStructMapping.class );

	protected DeptTreeUserRelId map(Long id){
		if (id == null) {
			return null;
		}
		return DeptTreeUserRelId.of(id);
	}
	protected Long map(DeptTreeUserRelId deptTreeUserRelId){
		if (deptTreeUserRelId == null) {
			return null;
		}
		return deptTreeUserRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptTreeUserRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param deptTreeUserRelDO
	 * @return
	 */
	public abstract DeptTreeUserRel deptTreeUserRelDOToDeptTreeUserRel(@MappingTarget DeptTreeUserRel deptTreeUserRel,DeptTreeUserRelDO deptTreeUserRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptTreeUserRelInfrastructureStructMapping#map(DeptTreeUserRelId)}
	 * @param deptTreeUserRel
	 * @return
	 */
	public abstract DeptTreeUserRelDO deptTreeUserRelToDeptTreeUserRelDO(DeptTreeUserRel deptTreeUserRel);

}
