package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementDO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncement;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业开庭公告 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyOpenCourtAnnouncementInfrastructureStructMapping {
	public static DataCompanyOpenCourtAnnouncementInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyOpenCourtAnnouncementInfrastructureStructMapping.class );

	protected DataCompanyOpenCourtAnnouncementId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyOpenCourtAnnouncementId.of(id);
	}
	protected Long map(DataCompanyOpenCourtAnnouncementId dataCompanyOpenCourtAnnouncementId){
		if (dataCompanyOpenCourtAnnouncementId == null) {
			return null;
		}
		return dataCompanyOpenCourtAnnouncementId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyOpenCourtAnnouncementInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyOpenCourtAnnouncementDO
	 * @return
	 */
	public abstract DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncementDOToDataCompanyOpenCourtAnnouncement(@MappingTarget DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement,DataCompanyOpenCourtAnnouncementDO dataCompanyOpenCourtAnnouncementDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyOpenCourtAnnouncementInfrastructureStructMapping#map(DataCompanyOpenCourtAnnouncementId)}
	 * @param dataCompanyOpenCourtAnnouncement
	 * @return
	 */
	public abstract DataCompanyOpenCourtAnnouncementDO dataCompanyOpenCourtAnnouncementToDataCompanyOpenCourtAnnouncementDO(DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement);

}
