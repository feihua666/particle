package com.particle.usagecount.infrastructure.structmapping;

import com.particle.usagecount.infrastructure.dos.UsageCountRecordDO;
import com.particle.usagecount.domain.UsageCountRecord;
import com.particle.usagecount.domain.UsageCountRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 使用次数记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Mapper
public abstract class UsageCountRecordInfrastructureStructMapping {
	public static UsageCountRecordInfrastructureStructMapping instance = Mappers.getMapper( UsageCountRecordInfrastructureStructMapping.class );

	protected UsageCountRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return UsageCountRecordId.of(id);
	}
	protected Long map(UsageCountRecordId usageCountRecordId){
		if (usageCountRecordId == null) {
			return null;
		}
		return usageCountRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param usageCountRecordDO
	 * @return
	 */
	public abstract UsageCountRecord usageCountRecordDOToUsageCountRecord(@MappingTarget UsageCountRecord usageCountRecord,UsageCountRecordDO usageCountRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountRecordInfrastructureStructMapping#map(UsageCountRecordId)}
	 * @param usageCountRecord
	 * @return
	 */
	public abstract UsageCountRecordDO usageCountRecordToUsageCountRecordDO(UsageCountRecord usageCountRecord);

}
