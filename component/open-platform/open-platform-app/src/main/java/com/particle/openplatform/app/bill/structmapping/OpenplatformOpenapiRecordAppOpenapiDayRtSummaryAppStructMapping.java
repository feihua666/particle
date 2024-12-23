package com.particle.openplatform.app.bill.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDayRtSummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放平台应用开放接口日实时汇总 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO>{
	public static OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping.class );

	protected Long map(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId openplatformOpenapiRecordAppOpenapiDayRtSummaryId){
		if (openplatformOpenapiRecordAppOpenapiDayRtSummaryId == null) {
			return null;
		}
		return openplatformOpenapiRecordAppOpenapiDayRtSummaryId.getId();
	}
	/**
	 * 开放平台应用开放接口日实时汇总领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping#map(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId)}
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummary
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO toOpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO(OpenplatformOpenapiRecordAppOpenapiDayRtSummary openplatformOpenapiRecordAppOpenapiDayRtSummary);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO openplatformOpenapiRecordAppOpenapiDayRtSummaryDOToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO openplatformOpenapiRecordAppOpenapiDayRtSummaryDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> openplatformOpenapiRecordAppOpenapiDayRtSummaryDOsToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryVOs(List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO> openplatformOpenapiRecordAppOpenapiDayRtSummaryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO> page) {
		return PageResponse.of(openplatformOpenapiRecordAppOpenapiDayRtSummaryDOsToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO pageQueryCommandToDO(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand);

	public abstract OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO queryListCommandToDO(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand);
}
