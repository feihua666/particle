package com.particle.dept.infrastructure.structmapping;

import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.dept.domain.Dept;
import com.particle.dept.domain.DeptId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 部门 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Mapper
public abstract class DeptInfrastructureStructMapping {
	public static DeptInfrastructureStructMapping instance = Mappers.getMapper( DeptInfrastructureStructMapping.class );

	protected DeptId map(Long id){
		if (id == null) {
			return null;
		}
		return DeptId.of(id);
	}
	protected Long map(DeptId deptId){
		if (deptId == null) {
			return null;
		}
		return deptId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptInfrastructureStructMapping#map(java.lang.Long)}
	 * @param deptDO
	 * @return
	 */
	public abstract Dept deptDOToDept(@MappingTarget Dept dept,DeptDO deptDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptInfrastructureStructMapping#map(DeptId)}
	 * @param dept
	 * @return
	 */
	public abstract DeptDO deptToDeptDO(Dept dept);

}
