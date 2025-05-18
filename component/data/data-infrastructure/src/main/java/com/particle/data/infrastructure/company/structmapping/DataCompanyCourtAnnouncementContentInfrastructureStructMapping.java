package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementContentDO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementContent;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementContentId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业法院公告内容 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyCourtAnnouncementContentInfrastructureStructMapping {
	public static DataCompanyCourtAnnouncementContentInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyCourtAnnouncementContentInfrastructureStructMapping.class );

	protected DataCompanyCourtAnnouncementContentId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyCourtAnnouncementContentId.of(id);
	}
	protected Long map(DataCompanyCourtAnnouncementContentId dataCompanyCourtAnnouncementContentId){
		if (dataCompanyCourtAnnouncementContentId == null) {
			return null;
		}
		return dataCompanyCourtAnnouncementContentId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCourtAnnouncementContentInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyCourtAnnouncementContentDO
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContentDOToDataCompanyCourtAnnouncementContent(@MappingTarget DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent,DataCompanyCourtAnnouncementContentDO dataCompanyCourtAnnouncementContentDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCourtAnnouncementContentInfrastructureStructMapping#map(DataCompanyCourtAnnouncementContentId)}
	 * @param dataCompanyCourtAnnouncementContent
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementContentDO dataCompanyCourtAnnouncementContentToDataCompanyCourtAnnouncementContentDO(DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent);

}
