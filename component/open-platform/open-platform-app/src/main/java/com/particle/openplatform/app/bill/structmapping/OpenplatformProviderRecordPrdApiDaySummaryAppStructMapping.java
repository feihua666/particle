package com.particle.openplatform.app.bill.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiDaySummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummary;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummaryId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiDaySummaryDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放平台供应商接口日汇总 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformProviderRecordPrdApiDaySummaryDO>{
	public static OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping instance = Mappers.getMapper( OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping.class );

	protected Long map(OpenplatformProviderRecordPrdApiDaySummaryId openplatformProviderRecordPrdApiDaySummaryId){
		if (openplatformProviderRecordPrdApiDaySummaryId == null) {
			return null;
		}
		return openplatformProviderRecordPrdApiDaySummaryId.getId();
	}
	/**
	 * 开放平台供应商接口日汇总领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping#map(OpenplatformProviderRecordPrdApiDaySummaryId)}
	 * @param openplatformProviderRecordPrdApiDaySummary
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdApiDaySummaryVO toOpenplatformProviderRecordPrdApiDaySummaryVO(OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary);


	/**
	 * 数据对象转视图对象
	 * @param openplatformProviderRecordPrdApiDaySummaryDO
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdApiDaySummaryVO openplatformProviderRecordPrdApiDaySummaryDOToOpenplatformProviderRecordPrdApiDaySummaryVO(OpenplatformProviderRecordPrdApiDaySummaryDO openplatformProviderRecordPrdApiDaySummaryDO);

	/**
	 * 批量转换
	 * @param openplatformProviderRecordPrdApiDaySummaryDOs
	 * @return
	 */
	public abstract List<OpenplatformProviderRecordPrdApiDaySummaryVO> openplatformProviderRecordPrdApiDaySummaryDOsToOpenplatformProviderRecordPrdApiDaySummaryVOs(List<OpenplatformProviderRecordPrdApiDaySummaryDO> openplatformProviderRecordPrdApiDaySummaryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> infrastructurePageToPageResponse(Page<OpenplatformProviderRecordPrdApiDaySummaryDO> page) {
		return PageResponse.of(openplatformProviderRecordPrdApiDaySummaryDOsToOpenplatformProviderRecordPrdApiDaySummaryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformProviderRecordPrdApiDaySummaryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand) {
			return queryListCommandToDO(((OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformProviderRecordPrdApiDaySummaryDO pageQueryCommandToDO(OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand openplatformProviderRecordPrdApiDaySummaryPageQueryCommand);

	public abstract OpenplatformProviderRecordPrdApiDaySummaryDO queryListCommandToDO(OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand openplatformProviderRecordPrdApiDaySummaryQueryListCommand);
}
