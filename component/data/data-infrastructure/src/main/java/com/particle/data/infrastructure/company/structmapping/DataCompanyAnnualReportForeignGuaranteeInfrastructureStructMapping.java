package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignGuaranteeDO;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuarantee;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuaranteeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业年报对外担保 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportForeignGuaranteeInfrastructureStructMapping {
	public static DataCompanyAnnualReportForeignGuaranteeInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportForeignGuaranteeInfrastructureStructMapping.class );

	protected DataCompanyAnnualReportForeignGuaranteeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAnnualReportForeignGuaranteeId.of(id);
	}
	protected Long map(DataCompanyAnnualReportForeignGuaranteeId dataCompanyAnnualReportForeignGuaranteeId){
		if (dataCompanyAnnualReportForeignGuaranteeId == null) {
			return null;
		}
		return dataCompanyAnnualReportForeignGuaranteeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportForeignGuaranteeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAnnualReportForeignGuaranteeDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuaranteeDOToDataCompanyAnnualReportForeignGuarantee(@MappingTarget DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee,DataCompanyAnnualReportForeignGuaranteeDO dataCompanyAnnualReportForeignGuaranteeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportForeignGuaranteeInfrastructureStructMapping#map(DataCompanyAnnualReportForeignGuaranteeId)}
	 * @param dataCompanyAnnualReportForeignGuarantee
	 * @return
	 */
	public abstract DataCompanyAnnualReportForeignGuaranteeDO dataCompanyAnnualReportForeignGuaranteeToDataCompanyAnnualReportForeignGuaranteeDO(DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee);

}
