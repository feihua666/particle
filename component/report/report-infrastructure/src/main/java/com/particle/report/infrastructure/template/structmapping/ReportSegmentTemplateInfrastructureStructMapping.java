package com.particle.report.infrastructure.template.structmapping;

import com.particle.report.infrastructure.template.dos.ReportSegmentTemplateDO;
import com.particle.report.domain.template.ReportSegmentTemplate;
import com.particle.report.domain.template.ReportSegmentTemplateId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 报告片段模板 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Mapper
public abstract class ReportSegmentTemplateInfrastructureStructMapping {
	public static ReportSegmentTemplateInfrastructureStructMapping instance = Mappers.getMapper( ReportSegmentTemplateInfrastructureStructMapping.class );

	protected ReportSegmentTemplateId map(Long id){
		if (id == null) {
			return null;
		}
		return ReportSegmentTemplateId.of(id);
	}
	protected Long map(ReportSegmentTemplateId reportSegmentTemplateId){
		if (reportSegmentTemplateId == null) {
			return null;
		}
		return reportSegmentTemplateId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link ReportSegmentTemplateInfrastructureStructMapping#map(java.lang.Long)}
	 * @param reportSegmentTemplateDO
	 * @return
	 */
	public abstract ReportSegmentTemplate reportSegmentTemplateDOToReportSegmentTemplate(@MappingTarget ReportSegmentTemplate reportSegmentTemplate,ReportSegmentTemplateDO reportSegmentTemplateDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link ReportSegmentTemplateInfrastructureStructMapping#map(ReportSegmentTemplateId)}
	 * @param reportSegmentTemplate
	 * @return
	 */
	public abstract ReportSegmentTemplateDO reportSegmentTemplateToReportSegmentTemplateDO(ReportSegmentTemplate reportSegmentTemplate);

}
