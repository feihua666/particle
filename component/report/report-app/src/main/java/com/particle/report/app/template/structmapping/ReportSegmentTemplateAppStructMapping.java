package com.particle.report.app.template.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;
import com.particle.report.domain.template.ReportSegmentTemplate;
import com.particle.report.domain.template.ReportSegmentTemplateId;
import com.particle.report.infrastructure.template.dos.ReportSegmentTemplateDO;
import com.particle.report.client.template.dto.command.representation.ReportSegmentTemplatePageQueryCommand;
import com.particle.report.client.template.dto.command.representation.ReportSegmentTemplateQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 报告片段模板 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReportSegmentTemplateAppStructMapping  implements IBaseQueryCommandMapStruct<ReportSegmentTemplateDO>{
	public static ReportSegmentTemplateAppStructMapping instance = Mappers.getMapper( ReportSegmentTemplateAppStructMapping.class );

	protected Long map(ReportSegmentTemplateId reportSegmentTemplateId){
		if (reportSegmentTemplateId == null) {
			return null;
		}
		return reportSegmentTemplateId.getId();
	}
	/**
	 * 报告片段模板领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link ReportSegmentTemplateAppStructMapping#map(ReportSegmentTemplateId)}
	 * @param reportSegmentTemplate
	 * @return
	 */
	public abstract ReportSegmentTemplateVO toReportSegmentTemplateVO(ReportSegmentTemplate reportSegmentTemplate);


	/**
	 * 数据对象转视图对象
	 * @param reportSegmentTemplateDO
	 * @return
	 */
	public abstract ReportSegmentTemplateVO reportSegmentTemplateDOToReportSegmentTemplateVO(ReportSegmentTemplateDO reportSegmentTemplateDO);

	/**
	 * 批量转换
	 * @param reportSegmentTemplateDOs
	 * @return
	 */
	public abstract List<ReportSegmentTemplateVO> reportSegmentTemplateDOsToReportSegmentTemplateVOs(List<ReportSegmentTemplateDO> reportSegmentTemplateDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<ReportSegmentTemplateVO> infrastructurePageToPageResponse(Page<ReportSegmentTemplateDO> page) {
		return PageResponse.of(reportSegmentTemplateDOsToReportSegmentTemplateVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public ReportSegmentTemplateDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof ReportSegmentTemplatePageQueryCommand) {
			return pageQueryCommandToDO((ReportSegmentTemplatePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof ReportSegmentTemplateQueryListCommand) {
			return queryListCommandToDO(((ReportSegmentTemplateQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract ReportSegmentTemplateDO pageQueryCommandToDO(ReportSegmentTemplatePageQueryCommand reportSegmentTemplatePageQueryCommand);

	public abstract ReportSegmentTemplateDO queryListCommandToDO(ReportSegmentTemplateQueryListCommand reportSegmentTemplateQueryListCommand);
}
