package com.particle.openplatform.client.bill.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdMonthBillVO;

/**
 * <p>
 * 开放平台供应商月账单 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformProviderRecordPrdMonthBillRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformProviderRecordPrdMonthBillQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformProviderRecordPrdMonthBillVO> queryList(OpenplatformProviderRecordPrdMonthBillQueryListCommand openplatformProviderRecordPrdMonthBillQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformProviderRecordPrdMonthBillPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformProviderRecordPrdMonthBillVO> pageQuery(OpenplatformProviderRecordPrdMonthBillPageQueryCommand openplatformProviderRecordPrdMonthBillPageQueryCommand);

}