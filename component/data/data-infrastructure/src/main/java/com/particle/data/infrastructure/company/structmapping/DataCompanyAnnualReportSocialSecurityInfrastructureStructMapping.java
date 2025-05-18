package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportSocialSecurityDO;
import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurity;
import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurityId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业年报社保 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportSocialSecurityInfrastructureStructMapping {
	public static DataCompanyAnnualReportSocialSecurityInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportSocialSecurityInfrastructureStructMapping.class );

	protected DataCompanyAnnualReportSocialSecurityId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAnnualReportSocialSecurityId.of(id);
	}
	protected Long map(DataCompanyAnnualReportSocialSecurityId dataCompanyAnnualReportSocialSecurityId){
		if (dataCompanyAnnualReportSocialSecurityId == null) {
			return null;
		}
		return dataCompanyAnnualReportSocialSecurityId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportSocialSecurityInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAnnualReportSocialSecurityDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurityDOToDataCompanyAnnualReportSocialSecurity(@MappingTarget DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity,DataCompanyAnnualReportSocialSecurityDO dataCompanyAnnualReportSocialSecurityDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportSocialSecurityInfrastructureStructMapping#map(DataCompanyAnnualReportSocialSecurityId)}
	 * @param dataCompanyAnnualReportSocialSecurity
	 * @return
	 */
	public abstract DataCompanyAnnualReportSocialSecurityDO dataCompanyAnnualReportSocialSecurityToDataCompanyAnnualReportSocialSecurityDO(DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity);

}
