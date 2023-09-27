package com.particle.report.infrastructure.reportapi.structmapping;

import com.particle.report.infrastructure.reportapi.dos.ReportReportApiDO;
import com.particle.report.domain.reportapi.ReportReportApi;
import com.particle.report.domain.reportapi.ReportReportApiId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 报告接口 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Mapper
public abstract class ReportReportApiInfrastructureStructMapping {
	public static ReportReportApiInfrastructureStructMapping instance = Mappers.getMapper( ReportReportApiInfrastructureStructMapping.class );

	protected ReportReportApiId map(Long id){
		if (id == null) {
			return null;
		}
		return ReportReportApiId.of(id);
	}
	protected Long map(ReportReportApiId reportReportApiId){
		if (reportReportApiId == null) {
			return null;
		}
		return reportReportApiId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link ReportReportApiInfrastructureStructMapping#map(java.lang.Long)}
	 * @param reportReportApiDO
	 * @return
	 */
	public abstract ReportReportApi reportReportApiDOToReportReportApi(@MappingTarget ReportReportApi reportReportApi,ReportReportApiDO reportReportApiDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link ReportReportApiInfrastructureStructMapping#map(ReportReportApiId)}
	 * @param reportReportApi
	 * @return
	 */
	public abstract ReportReportApiDO reportReportApiToReportReportApiDO(ReportReportApi reportReportApi);

}
