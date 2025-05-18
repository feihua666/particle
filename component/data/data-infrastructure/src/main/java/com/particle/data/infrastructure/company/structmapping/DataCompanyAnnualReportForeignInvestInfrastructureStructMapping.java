package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignInvestDO;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvest;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvestId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业年报对外投资 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportForeignInvestInfrastructureStructMapping {
	public static DataCompanyAnnualReportForeignInvestInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportForeignInvestInfrastructureStructMapping.class );

	protected DataCompanyAnnualReportForeignInvestId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAnnualReportForeignInvestId.of(id);
	}
	protected Long map(DataCompanyAnnualReportForeignInvestId dataCompanyAnnualReportForeignInvestId){
		if (dataCompanyAnnualReportForeignInvestId == null) {
			return null;
		}
		return dataCompanyAnnualReportForeignInvestId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportForeignInvestInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAnnualReportForeignInvestDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvestDOToDataCompanyAnnualReportForeignInvest(@MappingTarget DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest,DataCompanyAnnualReportForeignInvestDO dataCompanyAnnualReportForeignInvestDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportForeignInvestInfrastructureStructMapping#map(DataCompanyAnnualReportForeignInvestId)}
	 * @param dataCompanyAnnualReportForeignInvest
	 * @return
	 */
	public abstract DataCompanyAnnualReportForeignInvestDO dataCompanyAnnualReportForeignInvestToDataCompanyAnnualReportForeignInvestDO(DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest);

}
