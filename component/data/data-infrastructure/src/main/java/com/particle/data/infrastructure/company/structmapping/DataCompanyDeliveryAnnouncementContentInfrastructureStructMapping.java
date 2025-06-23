package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContent;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业送达公告内容 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyDeliveryAnnouncementContentInfrastructureStructMapping {
	public static DataCompanyDeliveryAnnouncementContentInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyDeliveryAnnouncementContentInfrastructureStructMapping.class );

	protected DataCompanyDeliveryAnnouncementContentId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyDeliveryAnnouncementContentId.of(id);
	}
	protected Long map(DataCompanyDeliveryAnnouncementContentId dataCompanyDeliveryAnnouncementContentId){
		if (dataCompanyDeliveryAnnouncementContentId == null) {
			return null;
		}
		return dataCompanyDeliveryAnnouncementContentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDeliveryAnnouncementContentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyDeliveryAnnouncementContentDO
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContentDOToDataCompanyDeliveryAnnouncementContent(@MappingTarget DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent,DataCompanyDeliveryAnnouncementContentDO dataCompanyDeliveryAnnouncementContentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDeliveryAnnouncementContentInfrastructureStructMapping#map(DataCompanyDeliveryAnnouncementContentId)}
	 * @param dataCompanyDeliveryAnnouncementContent
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementContentDO dataCompanyDeliveryAnnouncementContentToDataCompanyDeliveryAnnouncementContentDO(DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent);

}
