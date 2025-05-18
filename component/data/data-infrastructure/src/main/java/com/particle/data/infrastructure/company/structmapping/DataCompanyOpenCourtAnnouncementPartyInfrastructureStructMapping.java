package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementPartyDO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementParty;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementPartyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业开庭公告当事人 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyOpenCourtAnnouncementPartyInfrastructureStructMapping {
	public static DataCompanyOpenCourtAnnouncementPartyInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyOpenCourtAnnouncementPartyInfrastructureStructMapping.class );

	protected DataCompanyOpenCourtAnnouncementPartyId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyOpenCourtAnnouncementPartyId.of(id);
	}
	protected Long map(DataCompanyOpenCourtAnnouncementPartyId dataCompanyOpenCourtAnnouncementPartyId){
		if (dataCompanyOpenCourtAnnouncementPartyId == null) {
			return null;
		}
		return dataCompanyOpenCourtAnnouncementPartyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyOpenCourtAnnouncementPartyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyOpenCourtAnnouncementPartyDO
	 * @return
	 */
	public abstract DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementPartyDOToDataCompanyOpenCourtAnnouncementParty(@MappingTarget DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty,DataCompanyOpenCourtAnnouncementPartyDO dataCompanyOpenCourtAnnouncementPartyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyOpenCourtAnnouncementPartyInfrastructureStructMapping#map(DataCompanyOpenCourtAnnouncementPartyId)}
	 * @param dataCompanyOpenCourtAnnouncementParty
	 * @return
	 */
	public abstract DataCompanyOpenCourtAnnouncementPartyDO dataCompanyOpenCourtAnnouncementPartyToDataCompanyOpenCourtAnnouncementPartyDO(DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty);

}
