package com.particle.componentadmin.infrastructure.structmapping;

import com.particle.componentadmin.infrastructure.dos.AdminComponentDO;
import com.particle.componentadmin.domain.AdminComponent;
import com.particle.componentadmin.domain.AdminComponentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 组件 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AdminComponentInfrastructureStructMapping {
	public static AdminComponentInfrastructureStructMapping instance = Mappers.getMapper( AdminComponentInfrastructureStructMapping.class );

	protected AdminComponentId map(Long id){
		if (id == null) {
			return null;
		}
		return AdminComponentId.of(id);
	}
	protected Long map(AdminComponentId adminComponentId){
		if (adminComponentId == null) {
			return null;
		}
		return adminComponentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AdminComponentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param adminComponentDO
	 * @return
	 */
	public abstract AdminComponent adminComponentDOToAdminComponent(@MappingTarget AdminComponent adminComponent,AdminComponentDO adminComponentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AdminComponentInfrastructureStructMapping#map(AdminComponentId)}
	 * @param adminComponent
	 * @return
	 */
	public abstract AdminComponentDO adminComponentToAdminComponentDO(AdminComponent adminComponent);

}
