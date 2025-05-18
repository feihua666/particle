package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportDO;
import com.particle.data.domain.company.DataCompanyAnnualReport;
import com.particle.data.domain.company.DataCompanyAnnualReportId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业年报 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportInfrastructureStructMapping {
	public static DataCompanyAnnualReportInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportInfrastructureStructMapping.class );

	protected DataCompanyAnnualReportId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAnnualReportId.of(id);
	}
	protected Long map(DataCompanyAnnualReportId dataCompanyAnnualReportId){
		if (dataCompanyAnnualReportId == null) {
			return null;
		}
		return dataCompanyAnnualReportId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAnnualReportDO
	 * @return
	 */
	public abstract DataCompanyAnnualReport dataCompanyAnnualReportDOToDataCompanyAnnualReport(@MappingTarget DataCompanyAnnualReport dataCompanyAnnualReport,DataCompanyAnnualReportDO dataCompanyAnnualReportDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportInfrastructureStructMapping#map(DataCompanyAnnualReportId)}
	 * @param dataCompanyAnnualReport
	 * @return
	 */
	public abstract DataCompanyAnnualReportDO dataCompanyAnnualReportToDataCompanyAnnualReportDO(DataCompanyAnnualReport dataCompanyAnnualReport);

}
