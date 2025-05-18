package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportShareholderDO;
import com.particle.data.domain.company.DataCompanyAnnualReportShareholder;
import com.particle.data.domain.company.DataCompanyAnnualReportShareholderId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业年报股东 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportShareholderInfrastructureStructMapping {
	public static DataCompanyAnnualReportShareholderInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportShareholderInfrastructureStructMapping.class );

	protected DataCompanyAnnualReportShareholderId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAnnualReportShareholderId.of(id);
	}
	protected Long map(DataCompanyAnnualReportShareholderId dataCompanyAnnualReportShareholderId){
		if (dataCompanyAnnualReportShareholderId == null) {
			return null;
		}
		return dataCompanyAnnualReportShareholderId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportShareholderInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAnnualReportShareholderDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholderDOToDataCompanyAnnualReportShareholder(@MappingTarget DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder,DataCompanyAnnualReportShareholderDO dataCompanyAnnualReportShareholderDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportShareholderInfrastructureStructMapping#map(DataCompanyAnnualReportShareholderId)}
	 * @param dataCompanyAnnualReportShareholder
	 * @return
	 */
	public abstract DataCompanyAnnualReportShareholderDO dataCompanyAnnualReportShareholderToDataCompanyAnnualReportShareholderDO(DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder);

}
