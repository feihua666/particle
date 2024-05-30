package com.particle.config.infrastructure.system.structmapping;

import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.config.domain.system.SystemConfig;
import com.particle.config.domain.system.SystemConfigId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 系统参数配置 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Mapper
public abstract class SystemConfigInfrastructureStructMapping {
	public static SystemConfigInfrastructureStructMapping instance = Mappers.getMapper( SystemConfigInfrastructureStructMapping.class );

	protected SystemConfigId map(Long id){
		if (id == null) {
			return null;
		}
		return SystemConfigId.of(id);
	}
	protected Long map(SystemConfigId systemConfigId){
		if (systemConfigId == null) {
			return null;
		}
		return systemConfigId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SystemConfigInfrastructureStructMapping#map(java.lang.Long)}
	 * @param systemConfigDO
	 * @return
	 */
	public abstract SystemConfig systemConfigDOToSystemConfig(@MappingTarget SystemConfig systemConfig,SystemConfigDO systemConfigDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SystemConfigInfrastructureStructMapping#map(SystemConfigId)}
	 * @param systemConfig
	 * @return
	 */
	public abstract SystemConfigDO systemConfigToSystemConfigDO(SystemConfig systemConfig);

}
