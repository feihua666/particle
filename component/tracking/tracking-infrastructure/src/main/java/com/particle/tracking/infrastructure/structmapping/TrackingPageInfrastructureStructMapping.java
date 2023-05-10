package com.particle.tracking.infrastructure.structmapping;

import com.particle.tracking.infrastructure.dos.TrackingPageDO;
import com.particle.tracking.domain.TrackingPage;
import com.particle.tracking.domain.TrackingPageId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 埋点页面 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Mapper
public abstract class TrackingPageInfrastructureStructMapping {
	public static TrackingPageInfrastructureStructMapping instance = Mappers.getMapper( TrackingPageInfrastructureStructMapping.class );

	protected TrackingPageId map(Long id){
		if (id == null) {
			return null;
		}
		return TrackingPageId.of(id);
	}
	protected Long map(TrackingPageId trackingPageId){
		if (trackingPageId == null) {
			return null;
		}
		return trackingPageId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TrackingPageInfrastructureStructMapping#map(java.lang.Long)}
	 * @param trackingPageDO
	 * @return
	 */
	public abstract TrackingPage trackingPageDOToTrackingPage(@MappingTarget TrackingPage trackingPage,TrackingPageDO trackingPageDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link TrackingPageInfrastructureStructMapping#map(TrackingPageId)}
	 * @param trackingPage
	 * @return
	 */
	public abstract TrackingPageDO trackingPageToTrackingPageDO(TrackingPage trackingPage);

}
