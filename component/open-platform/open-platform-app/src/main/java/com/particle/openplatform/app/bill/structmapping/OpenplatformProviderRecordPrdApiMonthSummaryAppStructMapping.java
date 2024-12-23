package com.particle.openplatform.app.bill.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiMonthSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummary;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummaryId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiMonthSummaryDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放平台供应商接口月汇总 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformProviderRecordPrdApiMonthSummaryDO>{
	public static OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping instance = Mappers.getMapper( OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping.class );

	protected Long map(OpenplatformProviderRecordPrdApiMonthSummaryId openplatformProviderRecordPrdApiMonthSummaryId){
		if (openplatformProviderRecordPrdApiMonthSummaryId == null) {
			return null;
		}
		return openplatformProviderRecordPrdApiMonthSummaryId.getId();
	}
	/**
	 * 开放平台供应商接口月汇总领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping#map(OpenplatformProviderRecordPrdApiMonthSummaryId)}
	 * @param openplatformProviderRecordPrdApiMonthSummary
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdApiMonthSummaryVO toOpenplatformProviderRecordPrdApiMonthSummaryVO(OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary);


	/**
	 * 数据对象转视图对象
	 * @param openplatformProviderRecordPrdApiMonthSummaryDO
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdApiMonthSummaryVO openplatformProviderRecordPrdApiMonthSummaryDOToOpenplatformProviderRecordPrdApiMonthSummaryVO(OpenplatformProviderRecordPrdApiMonthSummaryDO openplatformProviderRecordPrdApiMonthSummaryDO);

	/**
	 * 批量转换
	 * @param openplatformProviderRecordPrdApiMonthSummaryDOs
	 * @return
	 */
	public abstract List<OpenplatformProviderRecordPrdApiMonthSummaryVO> openplatformProviderRecordPrdApiMonthSummaryDOsToOpenplatformProviderRecordPrdApiMonthSummaryVOs(List<OpenplatformProviderRecordPrdApiMonthSummaryDO> openplatformProviderRecordPrdApiMonthSummaryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> infrastructurePageToPageResponse(Page<OpenplatformProviderRecordPrdApiMonthSummaryDO> page) {
		return PageResponse.of(openplatformProviderRecordPrdApiMonthSummaryDOsToOpenplatformProviderRecordPrdApiMonthSummaryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformProviderRecordPrdApiMonthSummaryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand) {
			return queryListCommandToDO(((OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformProviderRecordPrdApiMonthSummaryDO pageQueryCommandToDO(OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand);

	public abstract OpenplatformProviderRecordPrdApiMonthSummaryDO queryListCommandToDO(OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand openplatformProviderRecordPrdApiMonthSummaryQueryListCommand);
}
