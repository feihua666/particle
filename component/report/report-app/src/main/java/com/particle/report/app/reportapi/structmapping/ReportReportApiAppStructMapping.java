package com.particle.report.app.reportapi.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.report.client.reportapi.dto.command.representation.ReportReportApiPageQueryCommand;
import com.particle.report.client.reportapi.dto.command.representation.ReportReportApiQueryListCommand;
import com.particle.report.client.reportapi.dto.data.ReportReportApiVO;
import com.particle.report.domain.reportapi.ReportReportApi;
import com.particle.report.domain.reportapi.ReportReportApiId;
import com.particle.report.infrastructure.reportapi.dos.ReportReportApiDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 报告接口 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReportReportApiAppStructMapping  implements IBaseQueryCommandMapStruct<ReportReportApiDO>{
	public static ReportReportApiAppStructMapping instance = Mappers.getMapper( ReportReportApiAppStructMapping.class );

	protected Long map(ReportReportApiId reportReportApiId){
		if (reportReportApiId == null) {
			return null;
		}
		return reportReportApiId.getId();
	}
	/**
	 * 报告接口领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link ReportReportApiAppStructMapping#map(ReportReportApiId)}
	 * @param reportReportApi
	 * @return
	 */
	public abstract ReportReportApiVO toReportReportApiVO(ReportReportApi reportReportApi);


	/**
	 * 数据对象转视图对象
	 * @param reportReportApiDO
	 * @return
	 */
	public abstract ReportReportApiVO reportReportApiDOToReportReportApiVO(ReportReportApiDO reportReportApiDO);

	/**
	 * 批量转换
	 * @param reportReportApiDOs
	 * @return
	 */
	public abstract List<ReportReportApiVO> reportReportApiDOsToReportReportApiVOs(List<ReportReportApiDO> reportReportApiDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<ReportReportApiVO> infrastructurePageToPageResponse(Page<ReportReportApiDO> page) {
		return PageResponse.of(reportReportApiDOsToReportReportApiVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public ReportReportApiDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof ReportReportApiPageQueryCommand) {
			return pageQueryCommandToDO((ReportReportApiPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof ReportReportApiQueryListCommand) {
			return queryListCommandToDO(((ReportReportApiQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract ReportReportApiDO pageQueryCommandToDO(ReportReportApiPageQueryCommand reportReportApiPageQueryCommand);

	public abstract ReportReportApiDO queryListCommandToDO(ReportReportApiQueryListCommand reportReportApiQueryListCommand);
}
