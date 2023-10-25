package com.particle.usagecount.infrastructure.structmapping;

import com.particle.usagecount.infrastructure.dos.UsageCountDefineDO;
import com.particle.usagecount.domain.UsageCountDefine;
import com.particle.usagecount.domain.UsageCountDefineId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 使用次数定义 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Mapper
public abstract class UsageCountDefineInfrastructureStructMapping {
	public static UsageCountDefineInfrastructureStructMapping instance = Mappers.getMapper( UsageCountDefineInfrastructureStructMapping.class );

	protected UsageCountDefineId map(Long id){
		if (id == null) {
			return null;
		}
		return UsageCountDefineId.of(id);
	}
	protected Long map(UsageCountDefineId usageCountDefineId){
		if (usageCountDefineId == null) {
			return null;
		}
		return usageCountDefineId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountDefineInfrastructureStructMapping#map(java.lang.Long)}
	 * @param usageCountDefineDO
	 * @return
	 */
	public abstract UsageCountDefine usageCountDefineDOToUsageCountDefine(@MappingTarget UsageCountDefine usageCountDefine,UsageCountDefineDO usageCountDefineDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountDefineInfrastructureStructMapping#map(UsageCountDefineId)}
	 * @param usageCountDefine
	 * @return
	 */
	public abstract UsageCountDefineDO usageCountDefineToUsageCountDefineDO(UsageCountDefine usageCountDefine);

}
