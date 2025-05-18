package com.particle.data.infrastructure.company.structmapping;

import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportChangeDO;
import com.particle.data.domain.company.DataCompanyAnnualReportChange;
import com.particle.data.domain.company.DataCompanyAnnualReportChangeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 企业年报变更 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAnnualReportChangeInfrastructureStructMapping {
	public static DataCompanyAnnualReportChangeInfrastructureStructMapping instance = Mappers.getMapper( DataCompanyAnnualReportChangeInfrastructureStructMapping.class );

	protected DataCompanyAnnualReportChangeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataCompanyAnnualReportChangeId.of(id);
	}
	protected Long map(DataCompanyAnnualReportChangeId dataCompanyAnnualReportChangeId){
		if (dataCompanyAnnualReportChangeId == null) {
			return null;
		}
		return dataCompanyAnnualReportChangeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportChangeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataCompanyAnnualReportChangeDO
	 * @return
	 */
	public abstract DataCompanyAnnualReportChange dataCompanyAnnualReportChangeDOToDataCompanyAnnualReportChange(@MappingTarget DataCompanyAnnualReportChange dataCompanyAnnualReportChange,DataCompanyAnnualReportChangeDO dataCompanyAnnualReportChangeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAnnualReportChangeInfrastructureStructMapping#map(DataCompanyAnnualReportChangeId)}
	 * @param dataCompanyAnnualReportChange
	 * @return
	 */
	public abstract DataCompanyAnnualReportChangeDO dataCompanyAnnualReportChangeToDataCompanyAnnualReportChangeDO(DataCompanyAnnualReportChange dataCompanyAnnualReportChange);

}
