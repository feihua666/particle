package com.particle.openplatform.app.bill.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBillId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppMonthBillDO;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppMonthBillQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放平台应用月账单 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiRecordAppMonthBillAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordAppMonthBillDO>{
	public static OpenplatformOpenapiRecordAppMonthBillAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppMonthBillAppStructMapping.class );

	protected Long map(OpenplatformOpenapiRecordAppMonthBillId openplatformOpenapiRecordAppMonthBillId){
		if (openplatformOpenapiRecordAppMonthBillId == null) {
			return null;
		}
		return openplatformOpenapiRecordAppMonthBillId.getId();
	}
	/**
	 * 开放平台应用月账单领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppMonthBillAppStructMapping#map(OpenplatformOpenapiRecordAppMonthBillId)}
	 * @param openplatformOpenapiRecordAppMonthBill
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppMonthBillVO toOpenplatformOpenapiRecordAppMonthBillVO(OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiRecordAppMonthBillDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppMonthBillVO openplatformOpenapiRecordAppMonthBillDOToOpenplatformOpenapiRecordAppMonthBillVO(OpenplatformOpenapiRecordAppMonthBillDO openplatformOpenapiRecordAppMonthBillDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiRecordAppMonthBillDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiRecordAppMonthBillVO> openplatformOpenapiRecordAppMonthBillDOsToOpenplatformOpenapiRecordAppMonthBillVOs(List<OpenplatformOpenapiRecordAppMonthBillDO> openplatformOpenapiRecordAppMonthBillDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordAppMonthBillVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiRecordAppMonthBillDO> page) {
		return PageResponse.of(openplatformOpenapiRecordAppMonthBillDOsToOpenplatformOpenapiRecordAppMonthBillVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiRecordAppMonthBillDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiRecordAppMonthBillPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiRecordAppMonthBillPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiRecordAppMonthBillQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiRecordAppMonthBillQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiRecordAppMonthBillDO pageQueryCommandToDO(OpenplatformOpenapiRecordAppMonthBillPageQueryCommand openplatformOpenapiRecordAppMonthBillPageQueryCommand);

	public abstract OpenplatformOpenapiRecordAppMonthBillDO queryListCommandToDO(OpenplatformOpenapiRecordAppMonthBillQueryListCommand openplatformOpenapiRecordAppMonthBillQueryListCommand);
}
