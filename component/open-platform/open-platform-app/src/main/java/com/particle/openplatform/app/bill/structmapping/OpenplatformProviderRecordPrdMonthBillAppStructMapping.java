package com.particle.openplatform.app.bill.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBillId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdMonthBillDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 开放平台供应商月账单 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformProviderRecordPrdMonthBillAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformProviderRecordPrdMonthBillDO>{
	public static OpenplatformProviderRecordPrdMonthBillAppStructMapping instance = Mappers.getMapper( OpenplatformProviderRecordPrdMonthBillAppStructMapping.class );

	protected Long map(OpenplatformProviderRecordPrdMonthBillId openplatformProviderRecordPrdMonthBillId){
		if (openplatformProviderRecordPrdMonthBillId == null) {
			return null;
		}
		return openplatformProviderRecordPrdMonthBillId.getId();
	}
	/**
	 * 开放平台供应商月账单领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordPrdMonthBillAppStructMapping#map(OpenplatformProviderRecordPrdMonthBillId)}
	 * @param openplatformProviderRecordPrdMonthBill
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdMonthBillVO toOpenplatformProviderRecordPrdMonthBillVO(OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill);


	/**
	 * 数据对象转视图对象
	 * @param openplatformProviderRecordPrdMonthBillDO
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdMonthBillVO openplatformProviderRecordPrdMonthBillDOToOpenplatformProviderRecordPrdMonthBillVO(OpenplatformProviderRecordPrdMonthBillDO openplatformProviderRecordPrdMonthBillDO);

	/**
	 * 批量转换
	 * @param openplatformProviderRecordPrdMonthBillDOs
	 * @return
	 */
	public abstract List<OpenplatformProviderRecordPrdMonthBillVO> openplatformProviderRecordPrdMonthBillDOsToOpenplatformProviderRecordPrdMonthBillVOs(List<OpenplatformProviderRecordPrdMonthBillDO> openplatformProviderRecordPrdMonthBillDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformProviderRecordPrdMonthBillVO> infrastructurePageToPageResponse(Page<OpenplatformProviderRecordPrdMonthBillDO> page) {
		return PageResponse.of(openplatformProviderRecordPrdMonthBillDOsToOpenplatformProviderRecordPrdMonthBillVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformProviderRecordPrdMonthBillDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformProviderRecordPrdMonthBillPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformProviderRecordPrdMonthBillPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformProviderRecordPrdMonthBillQueryListCommand) {
			return queryListCommandToDO(((OpenplatformProviderRecordPrdMonthBillQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformProviderRecordPrdMonthBillDO pageQueryCommandToDO(OpenplatformProviderRecordPrdMonthBillPageQueryCommand openplatformProviderRecordPrdMonthBillPageQueryCommand);

	public abstract OpenplatformProviderRecordPrdMonthBillDO queryListCommandToDO(OpenplatformProviderRecordPrdMonthBillQueryListCommand openplatformProviderRecordPrdMonthBillQueryListCommand);
}
