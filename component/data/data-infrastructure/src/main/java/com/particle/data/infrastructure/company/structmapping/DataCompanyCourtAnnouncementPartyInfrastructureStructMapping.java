package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementPartyDO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementParty;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementPartyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业法院公告当事人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyCourtAnnouncementPartyInfrastructureStructMapping {
	public static DataCompanyCourtAnnouncementPartyInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyCourtAnnouncementPartyInfrastructureStructMapping.class );

	protected DataCompanyCourtAnnouncementPartyId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyCourtAnnouncementPartyId.of(id);
	}
	protected Long map(DataCompanyCourtAnnouncementPartyId dataCompanyCourtAnnouncementPartyId){
		if (dataCompanyCourtAnnouncementPartyId == null) {
			return null;
		}
		return dataCompanyCourtAnnouncementPartyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCourtAnnouncementPartyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyCourtAnnouncementPartyDO
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementPartyDOToDataCompanyCourtAnnouncementParty(@MappingTarget DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty,DataCompanyCourtAnnouncementPartyDO dataCompanyCourtAnnouncementPartyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCourtAnnouncementPartyInfrastructureStructMapping#map(DataCompanyCourtAnnouncementPartyId)}
	 * @param dataCompanyCourtAnnouncementParty
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementPartyDO dataCompanyCourtAnnouncementPartyToDataCompanyCourtAnnouncementPartyDO(DataCompanyCourtAnnouncementParty dataCompanyCourtAnnouncementParty);

}
