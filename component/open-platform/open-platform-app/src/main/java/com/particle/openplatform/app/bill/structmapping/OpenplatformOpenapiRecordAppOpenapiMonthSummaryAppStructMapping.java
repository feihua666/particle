package com.particle.openplatform.app.bill.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummaryId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放平台应用开放接口月汇总 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO>{
	public static OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping.class );

	protected Long map(OpenplatformOpenapiRecordAppOpenapiMonthSummaryId openplatformOpenapiRecordAppOpenapiMonthSummaryId){
		if (openplatformOpenapiRecordAppOpenapiMonthSummaryId == null) {
			return null;
		}
		return openplatformOpenapiRecordAppOpenapiMonthSummaryId.getId();
	}
	/**
	 * 开放平台应用开放接口月汇总领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping#map(OpenplatformOpenapiRecordAppOpenapiMonthSummaryId)}
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummary
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO toOpenplatformOpenapiRecordAppOpenapiMonthSummaryVO(OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO openplatformOpenapiRecordAppOpenapiMonthSummaryDOToOpenplatformOpenapiRecordAppOpenapiMonthSummaryVO(OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO openplatformOpenapiRecordAppOpenapiMonthSummaryDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> openplatformOpenapiRecordAppOpenapiMonthSummaryDOsToOpenplatformOpenapiRecordAppOpenapiMonthSummaryVOs(List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> openplatformOpenapiRecordAppOpenapiMonthSummaryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> page) {
		return PageResponse.of(openplatformOpenapiRecordAppOpenapiMonthSummaryDOsToOpenplatformOpenapiRecordAppOpenapiMonthSummaryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO pageQueryCommandToDO(OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand);

	public abstract OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO queryListCommandToDO(OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand openplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand);
}
