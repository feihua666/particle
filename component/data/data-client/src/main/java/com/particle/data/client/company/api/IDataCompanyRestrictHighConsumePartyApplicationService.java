package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumePartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyRestrictHighConsumePartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumePartyVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyRestrictHighConsumePartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseVO;
/**
 * <p>
 * 企业限制高消费当事人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
public interface IDataCompanyRestrictHighConsumePartyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyRestrictHighConsumePartyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumePartyVO> create(DataCompanyRestrictHighConsumePartyCreateCommand dataCompanyRestrictHighConsumePartyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumePartyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyRestrictHighConsumePartyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumePartyVO> update(DataCompanyRestrictHighConsumePartyUpdateCommand dataCompanyRestrictHighConsumePartyUpdateCommand);

	/**
	 * 企业限制高消费当事人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> warehouse(DataCompanyRestrictHighConsumePartyWarehouseCommand dataCompanyBasicWarehouseCommand);
}