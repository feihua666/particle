package com.particle.usagecount.infrastructure.structmapping;

import com.particle.usagecount.domain.UsageCountRecordDetail;
import com.particle.usagecount.domain.UsageCountRecordDetailId;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDetailDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 使用次数记录明细 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UsageCountRecordDetailInfrastructureStructMapping {
	public static UsageCountRecordDetailInfrastructureStructMapping instance = Mappers.getMapper( UsageCountRecordDetailInfrastructureStructMapping.class );

	protected UsageCountRecordDetailId map(Long id){
		if (id == null) {
			return null;
		}
		return UsageCountRecordDetailId.of(id);
	}
	protected Long map(UsageCountRecordDetailId usageCountRecordDetailId){
		if (usageCountRecordDetailId == null) {
			return null;
		}
		return usageCountRecordDetailId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountRecordDetailInfrastructureStructMapping#map(java.lang.Long)}
	 * @param usageCountRecordDetailDO
	 * @return
	 */
	public abstract UsageCountRecordDetail usageCountRecordDetailDOToUsageCountRecordDetail(@MappingTarget UsageCountRecordDetail usageCountRecordDetail,UsageCountRecordDetailDO usageCountRecordDetailDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountRecordDetailInfrastructureStructMapping#map(UsageCountRecordDetailId)}
	 * @param usageCountRecordDetail
	 * @return
	 */
	public abstract UsageCountRecordDetailDO usageCountRecordDetailToUsageCountRecordDetailDO(UsageCountRecordDetail usageCountRecordDetail);

}
