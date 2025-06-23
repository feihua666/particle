package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementDO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncement;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业送达公告 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyDeliveryAnnouncementInfrastructureStructMapping {
	public static DataCompanyDeliveryAnnouncementInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyDeliveryAnnouncementInfrastructureStructMapping.class );

	protected DataCompanyDeliveryAnnouncementId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyDeliveryAnnouncementId.of(id);
	}
	protected Long map(DataCompanyDeliveryAnnouncementId dataCompanyDeliveryAnnouncementId){
		if (dataCompanyDeliveryAnnouncementId == null) {
			return null;
		}
		return dataCompanyDeliveryAnnouncementId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDeliveryAnnouncementInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyDeliveryAnnouncementDO
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncementDOToDataCompanyDeliveryAnnouncement(@MappingTarget DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement,DataCompanyDeliveryAnnouncementDO dataCompanyDeliveryAnnouncementDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDeliveryAnnouncementInfrastructureStructMapping#map(DataCompanyDeliveryAnnouncementId)}
	 * @param dataCompanyDeliveryAnnouncement
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementDO dataCompanyDeliveryAnnouncementToDataCompanyDeliveryAnnouncementDO(DataCompanyDeliveryAnnouncement dataCompanyDeliveryAnnouncement);

}
