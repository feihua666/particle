package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAdministrativeLicenseDO;
import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicense;
import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicenseId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业年报行政许可 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportAdministrativeLicenseInfrastructureStructMapping {
	public static DataCompanyAnnualReportAdministrativeLicenseInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportAdministrativeLicenseInfrastructureStructMapping.class );

	protected DataCompanyAnnualReportAdministrativeLicenseId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAnnualReportAdministrativeLicenseId.of(id);
	}
	protected Long map(DataCompanyAnnualReportAdministrativeLicenseId dataCompanyAnnualReportAdministrativeLicenseId){
		if (dataCompanyAnnualReportAdministrativeLicenseId == null) {
			return null;
		}
		return dataCompanyAnnualReportAdministrativeLicenseId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportAdministrativeLicenseInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAnnualReportAdministrativeLicenseDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicenseDOToDataCompanyAnnualReportAdministrativeLicense(@MappingTarget DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense,DataCompanyAnnualReportAdministrativeLicenseDO dataCompanyAnnualReportAdministrativeLicenseDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportAdministrativeLicenseInfrastructureStructMapping#map(DataCompanyAnnualReportAdministrativeLicenseId)}
	 * @param dataCompanyAnnualReportAdministrativeLicense
	 * @return
	 */
	public abstract DataCompanyAnnualReportAdministrativeLicenseDO dataCompanyAnnualReportAdministrativeLicenseToDataCompanyAnnualReportAdministrativeLicenseDO(DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense);

}
