package com.particle.usagecount.infrastructure.structmapping;

import com.particle.usagecount.domain.UsageCountConfig;
import com.particle.usagecount.domain.UsageCountConfigId;
import com.particle.usagecount.infrastructure.dos.UsageCountConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 使用次数配置 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UsageCountConfigInfrastructureStructMapping {
	public static UsageCountConfigInfrastructureStructMapping instance = Mappers.getMapper( UsageCountConfigInfrastructureStructMapping.class );

	protected UsageCountConfigId map(Long id){
		if (id == null) {
			return null;
		}
		return UsageCountConfigId.of(id);
	}
	protected Long map(UsageCountConfigId usageCountConfigId){
		if (usageCountConfigId == null) {
			return null;
		}
		return usageCountConfigId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountConfigInfrastructureStructMapping#map(java.lang.Long)}
	 * @param usageCountConfigDO
	 * @return
	 */
	public abstract UsageCountConfig usageCountConfigDOToUsageCountConfig(@MappingTarget UsageCountConfig usageCountConfig,UsageCountConfigDO usageCountConfigDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountConfigInfrastructureStructMapping#map(UsageCountConfigId)}
	 * @param usageCountConfig
	 * @return
	 */
	public abstract UsageCountConfigDO usageCountConfigToUsageCountConfigDO(UsageCountConfig usageCountConfig);

}
