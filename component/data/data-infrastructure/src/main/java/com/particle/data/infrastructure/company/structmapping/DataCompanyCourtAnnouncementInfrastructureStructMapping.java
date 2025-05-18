package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncement;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业法院公告 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyCourtAnnouncementInfrastructureStructMapping {
	public static DataCompanyCourtAnnouncementInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyCourtAnnouncementInfrastructureStructMapping.class );

	protected DataCompanyCourtAnnouncementId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyCourtAnnouncementId.of(id);
	}
	protected Long map(DataCompanyCourtAnnouncementId dataCompanyCourtAnnouncementId){
		if (dataCompanyCourtAnnouncementId == null) {
			return null;
		}
		return dataCompanyCourtAnnouncementId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCourtAnnouncementInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyCourtAnnouncementDO
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncement dataCompanyCourtAnnouncementDOToDataCompanyCourtAnnouncement(@MappingTarget DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement,DataCompanyCourtAnnouncementDO dataCompanyCourtAnnouncementDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCourtAnnouncementInfrastructureStructMapping#map(DataCompanyCourtAnnouncementId)}
	 * @param dataCompanyCourtAnnouncement
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementDO dataCompanyCourtAnnouncementToDataCompanyCourtAnnouncementDO(DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement);

}
