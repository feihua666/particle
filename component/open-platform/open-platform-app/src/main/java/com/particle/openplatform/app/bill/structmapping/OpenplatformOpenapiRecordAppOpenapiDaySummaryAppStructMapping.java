package com.particle.openplatform.app.bill.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDaySummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummaryId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放平台应用开放接口日汇总 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO>{
	public static OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping.class );

	protected Long map(OpenplatformOpenapiRecordAppOpenapiDaySummaryId openplatformOpenapiRecordAppOpenapiDaySummaryId){
		if (openplatformOpenapiRecordAppOpenapiDaySummaryId == null) {
			return null;
		}
		return openplatformOpenapiRecordAppOpenapiDaySummaryId.getId();
	}
	/**
	 * 开放平台应用开放接口日汇总领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping#map(OpenplatformOpenapiRecordAppOpenapiDaySummaryId)}
	 * @param openplatformOpenapiRecordAppOpenapiDaySummary
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiDaySummaryVO toOpenplatformOpenapiRecordAppOpenapiDaySummaryVO(OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiDaySummaryVO openplatformOpenapiRecordAppOpenapiDaySummaryDOToOpenplatformOpenapiRecordAppOpenapiDaySummaryVO(OpenplatformOpenapiRecordAppOpenapiDaySummaryDO openplatformOpenapiRecordAppOpenapiDaySummaryDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> openplatformOpenapiRecordAppOpenapiDaySummaryDOsToOpenplatformOpenapiRecordAppOpenapiDaySummaryVOs(List<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> openplatformOpenapiRecordAppOpenapiDaySummaryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> page) {
		return PageResponse.of(openplatformOpenapiRecordAppOpenapiDaySummaryDOsToOpenplatformOpenapiRecordAppOpenapiDaySummaryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiRecordAppOpenapiDaySummaryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiRecordAppOpenapiDaySummaryDO pageQueryCommandToDO(OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand);

	public abstract OpenplatformOpenapiRecordAppOpenapiDaySummaryDO queryListCommandToDO(OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand);
}
