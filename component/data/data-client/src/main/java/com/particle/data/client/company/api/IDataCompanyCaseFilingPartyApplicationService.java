package com.particle.data.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingPartyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyCaseFilingPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyCaseFilingPartyVO;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCaseFilingPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingPartyExWarehouseVO;
/**
 * <p>
 * 企业立案信息当事人 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
public interface IDataCompanyCaseFilingPartyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param dataCompanyCaseFilingPartyCreateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingPartyVO> create(DataCompanyCaseFilingPartyCreateCommand dataCompanyCaseFilingPartyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingPartyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param dataCompanyCaseFilingPartyUpdateCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingPartyVO> update(DataCompanyCaseFilingPartyUpdateCommand dataCompanyCaseFilingPartyUpdateCommand);

	/**
	 * 企业立案信息当事人入库
	 * @param dataCompanyBasicWarehouseCommand
	 * @return
	 */
	SingleResponse<DataCompanyCaseFilingPartyExWarehouseVO> warehouse(DataCompanyCaseFilingPartyWarehouseCommand dataCompanyBasicWarehouseCommand);
}