package com.particle.dept.infrastructure.structmapping;

import com.particle.dept.infrastructure.dos.DeptTreeDO;
import com.particle.dept.domain.DeptTree;
import com.particle.dept.domain.DeptTreeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 部门树 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Mapper
public abstract class DeptTreeInfrastructureStructMapping {
	public static DeptTreeInfrastructureStructMapping instance = Mappers.getMapper( DeptTreeInfrastructureStructMapping.class );

	protected DeptTreeId map(Long id){
		if (id == null) {
			return null;
		}
		return DeptTreeId.of(id);
	}
	protected Long map(DeptTreeId deptTreeId){
		if (deptTreeId == null) {
			return null;
		}
		return deptTreeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptTreeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param deptTreeDO
	 * @return
	 */
	public abstract DeptTree deptTreeDOToDeptTree(@MappingTarget DeptTree deptTree,DeptTreeDO deptTreeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptTreeInfrastructureStructMapping#map(DeptTreeId)}
	 * @param deptTree
	 * @return
	 */
	public abstract DeptTreeDO deptTreeToDeptTreeDO(DeptTree deptTree);

}
