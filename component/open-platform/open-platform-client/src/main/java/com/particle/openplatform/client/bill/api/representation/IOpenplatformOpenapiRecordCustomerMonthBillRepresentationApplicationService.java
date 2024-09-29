package com.particle.openplatform.client.bill.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordCustomerMonthBillVO;

/**
 * <p>
 * 开放平台客户月账单 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiRecordCustomerMonthBillRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiRecordCustomerMonthBillQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> queryList(OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand openplatformOpenapiRecordCustomerMonthBillQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiRecordCustomerMonthBillPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> pageQuery(OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand openplatformOpenapiRecordCustomerMonthBillPageQueryCommand);

}
