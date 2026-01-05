package com.particle.componentadmin.infrastructure.structmapping;

import com.particle.componentadmin.infrastructure.dos.AdminComponentDependencyDO;
import com.particle.componentadmin.domain.AdminComponentDependency;
import com.particle.componentadmin.domain.AdminComponentDependencyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 组件依赖关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AdminComponentDependencyInfrastructureStructMapping {
	public static AdminComponentDependencyInfrastructureStructMapping instance = Mappers.getMapper( AdminComponentDependencyInfrastructureStructMapping.class );

	protected AdminComponentDependencyId map(Long id){
		if (id == null) {
			return null;
		}
		return AdminComponentDependencyId.of(id);
	}
	protected Long map(AdminComponentDependencyId adminComponentDependencyId){
		if (adminComponentDependencyId == null) {
			return null;
		}
		return adminComponentDependencyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AdminComponentDependencyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param adminComponentDependencyDO
	 * @return
	 */
	public abstract AdminComponentDependency adminComponentDependencyDOToAdminComponentDependency(@MappingTarget AdminComponentDependency adminComponentDependency,AdminComponentDependencyDO adminComponentDependencyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AdminComponentDependencyInfrastructureStructMapping#map(AdminComponentDependencyId)}
	 * @param adminComponentDependency
	 * @return
	 */
	public abstract AdminComponentDependencyDO adminComponentDependencyToAdminComponentDependencyDO(AdminComponentDependency adminComponentDependency);

}
