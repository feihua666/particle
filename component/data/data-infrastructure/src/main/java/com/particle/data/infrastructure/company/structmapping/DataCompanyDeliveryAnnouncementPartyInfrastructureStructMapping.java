package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementPartyDO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementParty;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementPartyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业送达公告当事人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyDeliveryAnnouncementPartyInfrastructureStructMapping {
	public static DataCompanyDeliveryAnnouncementPartyInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyDeliveryAnnouncementPartyInfrastructureStructMapping.class );

	protected DataCompanyDeliveryAnnouncementPartyId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyDeliveryAnnouncementPartyId.of(id);
	}
	protected Long map(DataCompanyDeliveryAnnouncementPartyId dataCompanyDeliveryAnnouncementPartyId){
		if (dataCompanyDeliveryAnnouncementPartyId == null) {
			return null;
		}
		return dataCompanyDeliveryAnnouncementPartyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDeliveryAnnouncementPartyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyDeliveryAnnouncementPartyDO
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementPartyDOToDataCompanyDeliveryAnnouncementParty(@MappingTarget DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty,DataCompanyDeliveryAnnouncementPartyDO dataCompanyDeliveryAnnouncementPartyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDeliveryAnnouncementPartyInfrastructureStructMapping#map(DataCompanyDeliveryAnnouncementPartyId)}
	 * @param dataCompanyDeliveryAnnouncementParty
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementPartyDO dataCompanyDeliveryAnnouncementPartyToDataCompanyDeliveryAnnouncementPartyDO(DataCompanyDeliveryAnnouncementParty dataCompanyDeliveryAnnouncementParty);

}
