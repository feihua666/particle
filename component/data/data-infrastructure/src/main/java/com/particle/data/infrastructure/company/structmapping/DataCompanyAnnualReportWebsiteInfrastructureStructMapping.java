package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportWebsiteDO;
import com.particle.data.domain.company.DataCompanyAnnualReportWebsite;
import com.particle.data.domain.company.DataCompanyAnnualReportWebsiteId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业年报网站网店 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportWebsiteInfrastructureStructMapping {
	public static DataCompanyAnnualReportWebsiteInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportWebsiteInfrastructureStructMapping.class );

	protected DataCompanyAnnualReportWebsiteId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAnnualReportWebsiteId.of(id);
	}
	protected Long map(DataCompanyAnnualReportWebsiteId dataCompanyAnnualReportWebsiteId){
		if (dataCompanyAnnualReportWebsiteId == null) {
			return null;
		}
		return dataCompanyAnnualReportWebsiteId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportWebsiteInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAnnualReportWebsiteDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsiteDOToDataCompanyAnnualReportWebsite(@MappingTarget DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite,DataCompanyAnnualReportWebsiteDO dataCompanyAnnualReportWebsiteDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportWebsiteInfrastructureStructMapping#map(DataCompanyAnnualReportWebsiteId)}
	 * @param dataCompanyAnnualReportWebsite
	 * @return
	 */
	public abstract DataCompanyAnnualReportWebsiteDO dataCompanyAnnualReportWebsiteToDataCompanyAnnualReportWebsiteDO(DataCompanyAnnualReportWebsite dataCompanyAnnualReportWebsite);

}
