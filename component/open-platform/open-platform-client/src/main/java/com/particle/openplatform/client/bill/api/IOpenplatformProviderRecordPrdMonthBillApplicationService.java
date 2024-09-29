package com.particle.openplatform.client.bill.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdMonthBillVO;
/**
 * <p>
 * 开放平台供应商月账单 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
public interface IOpenplatformProviderRecordPrdMonthBillApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformProviderRecordPrdMonthBillCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> create(OpenplatformProviderRecordPrdMonthBillCreateCommand openplatformProviderRecordPrdMonthBillCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformProviderRecordPrdMonthBillUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> update(OpenplatformProviderRecordPrdMonthBillUpdateCommand openplatformProviderRecordPrdMonthBillUpdateCommand);
}
