package com.particle.tracking.infrastructure.structmapping;

import com.particle.tracking.domain.TrackingPageRecord;
import com.particle.tracking.domain.TrackingPageRecordId;
import com.particle.tracking.infrastructure.dos.TrackingPageRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 页面埋点记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TrackingPageRecordInfrastructureStructMapping {
	public static TrackingPageRecordInfrastructureStructMapping instance = Mappers.getMapper( TrackingPageRecordInfrastructureStructMapping.class );

	protected TrackingPageRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return TrackingPageRecordId.of(id);
	}
	protected Long map(TrackingPageRecordId trackingPageRecordId){
		if (trackingPageRecordId == null) {
			return null;
		}
		return trackingPageRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TrackingPageRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param trackingPageRecordDO
	 * @return
	 */
	public abstract TrackingPageRecord trackingPageRecordDOToTrackingPageRecord(@MappingTarget TrackingPageRecord trackingPageRecord,TrackingPageRecordDO trackingPageRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TrackingPageRecordInfrastructureStructMapping#map(TrackingPageRecordId)}
	 * @param trackingPageRecord
	 * @return
	 */
	public abstract TrackingPageRecordDO trackingPageRecordToTrackingPageRecordDO(TrackingPageRecord trackingPageRecord);

}
