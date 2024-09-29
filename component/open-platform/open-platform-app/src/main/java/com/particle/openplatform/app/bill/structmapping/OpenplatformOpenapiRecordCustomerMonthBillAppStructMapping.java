package com.particle.openplatform.app.bill.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordCustomerMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBillId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordCustomerMonthBillDO;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放平台客户月账单 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiRecordCustomerMonthBillDO>{
	public static OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping.class );

	protected Long map(OpenplatformOpenapiRecordCustomerMonthBillId openplatformOpenapiRecordCustomerMonthBillId){
		if (openplatformOpenapiRecordCustomerMonthBillId == null) {
			return null;
		}
		return openplatformOpenapiRecordCustomerMonthBillId.getId();
	}
	/**
	 * 开放平台客户月账单领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping#map(OpenplatformOpenapiRecordCustomerMonthBillId)}
	 * @param openplatformOpenapiRecordCustomerMonthBill
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordCustomerMonthBillVO toOpenplatformOpenapiRecordCustomerMonthBillVO(OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiRecordCustomerMonthBillDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordCustomerMonthBillVO openplatformOpenapiRecordCustomerMonthBillDOToOpenplatformOpenapiRecordCustomerMonthBillVO(OpenplatformOpenapiRecordCustomerMonthBillDO openplatformOpenapiRecordCustomerMonthBillDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiRecordCustomerMonthBillDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiRecordCustomerMonthBillVO> openplatformOpenapiRecordCustomerMonthBillDOsToOpenplatformOpenapiRecordCustomerMonthBillVOs(List<OpenplatformOpenapiRecordCustomerMonthBillDO> openplatformOpenapiRecordCustomerMonthBillDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiRecordCustomerMonthBillDO> page) {
		return PageResponse.of(openplatformOpenapiRecordCustomerMonthBillDOsToOpenplatformOpenapiRecordCustomerMonthBillVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiRecordCustomerMonthBillDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiRecordCustomerMonthBillDO pageQueryCommandToDO(OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand openplatformOpenapiRecordCustomerMonthBillPageQueryCommand);

	public abstract OpenplatformOpenapiRecordCustomerMonthBillDO queryListCommandToDO(OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand openplatformOpenapiRecordCustomerMonthBillQueryListCommand);
}
