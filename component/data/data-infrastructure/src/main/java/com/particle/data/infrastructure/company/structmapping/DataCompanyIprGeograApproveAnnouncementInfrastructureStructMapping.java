package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograApproveAnnouncementDO;
import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncement;
import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncementId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业知识产权地理标识核准公告 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprGeograApproveAnnouncementInfrastructureStructMapping {
	public static DataCompanyIprGeograApproveAnnouncementInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyIprGeograApproveAnnouncementInfrastructureStructMapping.class );

	protected DataCompanyIprGeograApproveAnnouncementId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyIprGeograApproveAnnouncementId.of(id);
	}
	protected Long map(DataCompanyIprGeograApproveAnnouncementId dataCompanyIprGeograApproveAnnouncementId){
		if (dataCompanyIprGeograApproveAnnouncementId == null) {
			return null;
		}
		return dataCompanyIprGeograApproveAnnouncementId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprGeograApproveAnnouncementInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyIprGeograApproveAnnouncementDO
	 * @return
	 */
	public abstract DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncementDOToDataCompanyIprGeograApproveAnnouncement(@MappingTarget DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement,DataCompanyIprGeograApproveAnnouncementDO dataCompanyIprGeograApproveAnnouncementDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprGeograApproveAnnouncementInfrastructureStructMapping#map(DataCompanyIprGeograApproveAnnouncementId)}
	 * @param dataCompanyIprGeograApproveAnnouncement
	 * @return
	 */
	public abstract DataCompanyIprGeograApproveAnnouncementDO dataCompanyIprGeograApproveAnnouncementToDataCompanyIprGeograApproveAnnouncementDO(DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement);

}
