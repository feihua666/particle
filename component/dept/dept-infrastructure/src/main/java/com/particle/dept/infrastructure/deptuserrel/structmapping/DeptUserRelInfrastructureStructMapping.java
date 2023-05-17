package com.particle.dept.infrastructure.deptuserrel.structmapping;

import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.dept.domain.deptuserrel.DeptUserRel;
import com.particle.dept.domain.deptuserrel.DeptUserRelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 部门用户关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Mapper
public abstract class DeptUserRelInfrastructureStructMapping {
	public static DeptUserRelInfrastructureStructMapping instance = Mappers.getMapper( DeptUserRelInfrastructureStructMapping.class );

	protected DeptUserRelId map(Long id){
		if (id == null) {
			return null;
		}
		return DeptUserRelId.of(id);
	}
	protected Long map(DeptUserRelId deptUserRelId){
		if (deptUserRelId == null) {
			return null;
		}
		return deptUserRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptUserRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param deptUserRelDO
	 * @return
	 */
	public abstract DeptUserRel deptUserRelDOToDeptUserRel(@MappingTarget DeptUserRel deptUserRel,DeptUserRelDO deptUserRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptUserRelInfrastructureStructMapping#map(DeptUserRelId)}
	 * @param deptUserRel
	 * @return
	 */
	public abstract DeptUserRelDO deptUserRelToDeptUserRelDO(DeptUserRel deptUserRel);

}
