package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportEquityChangeDO;
import com.particle.data.domain.company.DataCompanyAnnualReportEquityChange;
import com.particle.data.domain.company.DataCompanyAnnualReportEquityChangeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业年报股权变更 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportEquityChangeInfrastructureStructMapping {
	public static DataCompanyAnnualReportEquityChangeInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportEquityChangeInfrastructureStructMapping.class );

	protected DataCompanyAnnualReportEquityChangeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAnnualReportEquityChangeId.of(id);
	}
	protected Long map(DataCompanyAnnualReportEquityChangeId dataCompanyAnnualReportEquityChangeId){
		if (dataCompanyAnnualReportEquityChangeId == null) {
			return null;
		}
		return dataCompanyAnnualReportEquityChangeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportEquityChangeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAnnualReportEquityChangeDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChangeDOToDataCompanyAnnualReportEquityChange(@MappingTarget DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange,DataCompanyAnnualReportEquityChangeDO dataCompanyAnnualReportEquityChangeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportEquityChangeInfrastructureStructMapping#map(DataCompanyAnnualReportEquityChangeId)}
	 * @param dataCompanyAnnualReportEquityChange
	 * @return
	 */
	public abstract DataCompanyAnnualReportEquityChangeDO dataCompanyAnnualReportEquityChangeToDataCompanyAnnualReportEquityChangeDO(DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange);

}
