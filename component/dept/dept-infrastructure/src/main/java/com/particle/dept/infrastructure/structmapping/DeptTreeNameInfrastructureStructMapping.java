package com.particle.dept.infrastructure.structmapping;

import com.particle.dept.domain.DeptTreeName;
import com.particle.dept.domain.DeptTreeNameId;
import com.particle.dept.infrastructure.dos.DeptTreeNameDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 部门树名称 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DeptTreeNameInfrastructureStructMapping {
	public static DeptTreeNameInfrastructureStructMapping instance = Mappers.getMapper( DeptTreeNameInfrastructureStructMapping.class );

	protected DeptTreeNameId map(Long id){
		if (id == null) {
			return null;
		}
		return DeptTreeNameId.of(id);
	}
	protected Long map(DeptTreeNameId deptTreeNameId){
		if (deptTreeNameId == null) {
			return null;
		}
		return deptTreeNameId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptTreeNameInfrastructureStructMapping#map(java.lang.Long)}
	 * @param deptTreeNameDO
	 * @return
	 */
	public abstract DeptTreeName deptTreeNameDOToDeptTreeName(@MappingTarget DeptTreeName deptTreeName,DeptTreeNameDO deptTreeNameDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptTreeNameInfrastructureStructMapping#map(DeptTreeNameId)}
	 * @param deptTreeName
	 * @return
	 */
	public abstract DeptTreeNameDO deptTreeNameToDeptTreeNameDO(DeptTreeName deptTreeName);

}
